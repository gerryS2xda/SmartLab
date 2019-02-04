package presentation.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.*;
import com.project.utils.Utils;

import businessLogic.prenotazione.PrenotazioneException;
import businessLogic.prenotazione.PrenotazioneManager;
import dataAccess.storage.bean.Prenotazione;
import dataAccess.storage.bean.Studente;

@WebServlet("/prenotazione-serv")
public class ServletPrenotazioneManagement extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrenotazioneManager manager = PrenotazioneManager.getInstance();
		HttpSession session = request.getSession();
		Gson json = new Gson();
		
		String action = request.getParameter("action");
		if(action == null){
			response.setStatus(404);
			response.sendRedirect("./index.jsp"); //pagina errore 404
		}else if(action.equals("effettua")){
			boolean done = true;
			
			//ottieni oggetto studente da sessione
			Studente s = (Studente) session.getAttribute("user");
			
			String lab = request.getParameter("lab"); //usare Postazione obj per ottenere il lab
			
			int post = Integer.parseInt(request.getParameter("postazione")); //usare postazione repository per ottenere obj Postazione
			
			String inizio = request.getParameter("inizio");
			String fine =  request.getParameter("fine");
			
			//costruisci risposta JSON
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			
			try{
				manager.effettuaPrenotazione(s.getEmail(), post, inizio, fine, lab);
			}catch (PrenotazioneException e){
				done = false;
			}

			if(done){
				response.getWriter().write(json.toJson("{\"esito\": \"ok\"}"));
			}else{
				response.getWriter().write(json.toJson("{\"esito\": \"failure\"}"));
			}
			
		}else if(action.equals("check_post")){
			
			//costruisci risposta JSON
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			
			String lab = request.getParameter("lab"); //usare Postazione obj per ottenere il lab
			String post = request.getParameter("postazione"); //usare postazione repository per ottenere obj Postazione
			String inizio = request.getParameter("inizio");
			String fine =  request.getParameter("fine");
			
			//collegarsi con la repository di postazione per ottenere un oggetto Postazione da cui vedere se e' disponibile
			List<Prenotazione> prenotazioni = manager.getPrenotazioniByQuery(inizio, fine, post, lab);
			if(prenotazioni.size() == 0){
				response.getWriter().write(json.toJson("{\"status\": \"disponibile\"}"));
			}else{
				response.getWriter().write(json.toJson("{\"status\": \"occupata\"}"));
			}
		}else if(action.equals("lista_pren_by_stud")){
			//costruisci risposta JSON
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			
			Studente s = (Studente) session.getAttribute("user");
			
			String str = "{";
			List<Prenotazione> prenotazioni = manager.getListPrenotazioniByStudent(s.getEmail());
			int i = 0;
			for(Prenotazione p : prenotazioni){
				str+= "\"pren" + i + "\":" + p.toString() + ",";
				i++;
			}
			str = str.substring(0, str.length() - 1) + "}"; //rimuovi ultima ',' e poi aggiungi '}'
			response.getWriter().write(json.toJson(str));
		}else if(action.equals("pren_status")){
			//costruisci risposta JSON
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			
			int id = Integer.parseInt(request.getParameter("id_pren"));
			Prenotazione pr = new Prenotazione();
			
			try{
				pr = manager.findPrenotazioneById(id);
			}catch(PrenotazioneException e){
				response.getWriter().write(json.toJson("{\"status\": \"notValid\"}")); //id non valido
			}
			
			if(manager.isPrenotazioneActive(pr)){
				response.getWriter().write(json.toJson("{\"status\": \"active\"}"));
			}else{
				//procedi alla modifica dello stato di questa postazione
				pr.setStatus(false);
				manager.updatePrenotazione(pr);
				
				response.getWriter().write(json.toJson("{\"status\": \"scaduta\"}"));
			}
		}else if(action.equals("del_pren")){
			boolean done = true;
			
			//costruisci risposta JSON
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			
			int id = Integer.parseInt(request.getParameter("id_pren"));
			Prenotazione pr = new Prenotazione();
			try{
				pr = manager.findPrenotazioneById(id);
			}catch (PrenotazioneException e){
				//id non valido oppure la prenotazione non si puo' annullare
				done = false;
			}
			
			try {
				manager.annullaPrenotazione(pr);
			} catch (PrenotazioneException e) {
				done = false;
			}

			if(done){
				response.getWriter().write(json.toJson("{\"esito\": \"true\"}"));
			}else{
				response.getWriter().write(json.toJson("{\"esito\": \"false\"}"));
			}
		}else if(action.equals("numero_post_occupate")){
			
			//costruisci risposta JSON
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			
			String oraInizio = request.getParameter("ora_inizio");
			String idLab = request.getParameter("idLab");
			
			int num = manager.getNumeroPostazioniPrenotate(oraInizio, idLab);
			
			response.getWriter().write(json.toJson("{\"numeroPost\": " + num + " }"));
		}else if(action.equals("del_pren_after_24")){	//cancella le prenotazioni dopo 24 ore
			boolean done = true;
			
			//costruisci risposta JSON
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			
			Studente s = (Studente) session.getAttribute("user");
			
			try{
				manager.deletePrenotazioniAfter24Hour(s.getEmail());
			}catch(SQLException e){
				done = false;
			}
			
			if(done){
				response.getWriter().write(json.toJson("{\"esito\": \"true\"}"));
			}else{
				response.getWriter().write(json.toJson("{\"esito\": \"false\"}"));
			}
		}else if(action.equals("lista_pren")){
			//costruisci risposta JSON
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			
			String str = "{";
			List<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();
			try {
				prenotazioni = manager.getAllPrenotazioni();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int i = 0;
			for(Prenotazione p : prenotazioni){
				str+= "\"pren" + i + "\":" + p.toString() + ",";
				i++;
			}
			str = str.substring(0, str.length() - 1) + "}"; //rimuovi ultima ',' e poi aggiungi '}'
			response.getWriter().write(json.toJson(str));
		}
	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
