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
			
			manager.effettuaPrenotazione(stud, post, inizio, fine);
			
			//costruisci risposta JSON
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			
			response.getWriter().write(json.toJson("{\"esito\": \"ok\"}"));
		}else if(action.equals("check_post")){
			
			//costruisci risposta JSON
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			
			String lab = request.getParameter("lab"); //usare Postazione obj per ottenere il lab
			int post = Integer.parseInt(request.getParameter("postazione")); //usare postazione repository per ottenere obj Postazione
			String inizio = request.getParameter("inizio");
			String fine =  request.getParameter("fine");
			
			//collegarsi con la repository di postazione per ottenere un oggetto Postazione da cui vedere se e' disponibile
			
			response.getWriter().write(json.toJson("{\"esito\": \"ok\"}"));
			boolean statusPost = true;
			if(statusPost){
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
			Prenotazione pr = manager.findPrenotazioneById(id);
			
			if(manager.isPrenotazioneActive(pr)){
				response.getWriter().write(json.toJson("{\"status\": \"active\"}"));
			}else{
				//procedi alla modifica dello stato di questa postazione
				pr.setStatus(false);
				manager.updatePrenotazione(pr);
				
				response.getWriter().write(json.toJson("{\"status\": \"scaduta\"}"));
			}
		}else if(action.equals("del_pren")){
			
			int id = Integer.parseInt(request.getParameter("id_pren"));
			Prenotazione pr = manager.findPrenotazioneById(id);
			manager.annullaPrenotazione(pr);
			
		}
	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
