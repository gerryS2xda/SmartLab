package presentation.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.*;

import businessLogic.comunicazione.CommunicationManager;
import dataAccess.storage.bean.Segnalazione;

public class ServletSegnalazione extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Gson json = new Gson();
		String segnalazione = request.getParameter("invioSegnalazione");
		CommunicationManager cm = new CommunicationManager();
		if(segnalazione == null){
			response.setStatus(404);
			response.sendRedirect("./Errore.jsp");
		}else if(segnalazione.equals("newSegnalazione")){
			List<Segnalazione> lista = cm.viewSegnalazione();
			int count = 0, id = -1;
			while(count < lista.size()){
				if(lista.get(count).getId() >= id)
					id = lista.get(count).getId() + 1;
				count++;
			}
			String oggetto = request.getParameter("oggetto");
			String descrizione = request.getParameter("descrizione");
			String lab = request.getParameter("lab");
			int pos = Integer.parseInt(request.getParameter("pos"));
			int studente = Integer.parseInt(request.getParameter("studente"));
			java.util.Date d = new java.util.Date();
			Date data = new Date(d.getTime());
			Segnalazione s = new Segnalazione(id, oggetto, descrizione, data, studente, lab, pos);
			cm.addSegnalazione(s);
		}else if(segnalazione.equals("deleteSegnalazione")){
			int id = Integer.parseInt(request.getParameter("id"));
			String oggetto = request.getParameter("oggetto");
			String descrizione = request.getParameter("descrizione");
			String lab = request.getParameter("lab");
			int pos = Integer.parseInt(request.getParameter("pos"));
			int studente = Integer.parseInt(request.getParameter("studente"));
			java.util.Date d = new java.util.Date();
			Date data = new Date(d.getTime());
			Segnalazione s = new Segnalazione(id, oggetto, descrizione, data, studente, lab, pos);
			cm.deleteSegnalazione(s);
		}else if(segnalazione.equals("viewSegnalazioni")){
			response.setContentType("application/json");
			int count = 0;
			List<Segnalazione> lista = cm.viewSegnalazione();
			while(count < lista.size()){
				response.getWriter().write(json.toJson("{\"id\": \"" + lista.get(count).getId() + "\", \"oggetto\": \"" + lista.get(count).getOggetto() + "\", \"descrizione\": \"" + lista.get(count).getDescrizione() + "\", \"data\": \"" + lista.get(count).getData() + "\", \"studente\": \"" + lista.get(count).getStudente() + "\", \"laboratorio\": \"" + lista.get(count).getLaboratorio() + "\", \"postazione\": \"" + lista.get(count).getPostazione() + "\"}"));
			}
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doGet(request, response);
	}
}
