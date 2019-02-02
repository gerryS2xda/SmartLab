package presentation.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.*;
import com.project.utils.Utils;

import businessLogic.prenotazione.PrenotazioneException;
import businessLogic.prenotazione.PrenotazioneManager;
import dataAccess.storage.bean.Prenotazione;

@WebServlet("/prenotazione-serv")
public class ServletPrenotazioneManagement extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrenotazioneManager manager = PrenotazioneManager.getInstance();
		Gson json = new Gson();
		
		String action = request.getParameter("action");
		if(action == null){
			response.setStatus(404);
			response.sendRedirect("./index.jsp"); //pagina errore 404
		}else if(action.equals("effettua")){
			//ottieni oggetto studente da sessione
			String stud = "g.laucella@studenti.unisa.it";	//da sostituire con oggetto
			String lab = request.getParameter("lab"); //usare Postazione obj per ottenere il lab
			
			int post = Integer.parseInt(request.getParameter("postazione")); //usare postazione repository per ottenere obj Postazione
			
			String inizio = request.getParameter("inizio");
			String fine =  request.getParameter("fine");
			
			//costruisci risposta JSON
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			
			try{
				manager.effettuaPrenotazione(stud, post, inizio, fine, lab);
			}catch (PrenotazioneException e){
				response.getWriter().write(json.toJson("{\"esito\": \"failure\"}"));
			}

			response.getWriter().write(json.toJson("{\"esito\": \"ok\"}"));
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
		}else if(action.equals("lista_pren")){
			//costruisci risposta JSON
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			
			String stud = "g.laucella@studenti.unisa.it"; //ottiene mail da obj Student presente in sessione
			
			String str = "{";
			List<Prenotazione> prenotazioni = manager.getListPrenotazioniByStudent(stud);
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
				manager.findPrenotazioneById(id);
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
			
			//costruisci risposta JSON
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			
			int id = Integer.parseInt(request.getParameter("id_pren"));
			try{
				Prenotazione pr = manager.findPrenotazioneById(id);
				manager.annullaPrenotazione(pr);
			}catch (PrenotazioneException e){
				//id non valido oppure la prenotazione non si puo' annullare
				response.getWriter().write(json.toJson("{\"esito\": \"failure\"}"));
			}

			response.getWriter().write(json.toJson("{\"esito\": \"ok\"}"));
		}else if(action.equals("numero_post_occupate")){
			
			//costruisci risposta JSON
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			
			String oraInizio = request.getParameter("ora_inizio");
			
			int num = manager.getNumeroPostazioniPrenotate(oraInizio);
			
			response.getWriter().write(json.toJson("{\"numeroPost\": " + num + " }"));
		}
	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
