package presentation.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.google.gson.Gson;
import businessLogic.comunicazione.CommunicationManager;
import dataAccess.storage.bean.Segnalazione;
import dataAccess.storage.bean.Studente;

@WebServlet("/ServletSegnalazione")
public class ServletSegnalazione extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();
		Gson json = new Gson();
		String segnalazione = request.getParameter("action");
		CommunicationManager cm = new CommunicationManager();
		if(segnalazione == null){
			response.setStatus(404);
			response.sendRedirect("./error.jsp");
		}else if(segnalazione.equals("newSegnalazione")){
			List<Segnalazione> lista = cm.viewSegnalazione();
			int count = 0, id = -1;
			while(count < lista.size()){
				if(lista.get(count).getId() >= id) {
					id = lista.get(count).getId() + 1;
				}
				count++;
			}
			Studente st = (Studente) session.getAttribute("user");
			String oggetto = request.getParameter("oggetto");
			String descrizione = request.getParameter("descrizione");
			String lab = request.getParameter("laboratorio");
			int pos = Integer.parseInt(request.getParameter("postazione"));
			String studente = st.getEmail();
			java.util.Date d = new java.util.Date();
			Date data = new Date(d.getTime());
			Segnalazione s = new Segnalazione(id, oggetto, descrizione, data, studente, lab, pos);
			response.setContentType("aplication/json");
			if(cm.addSegnalazione(s)) {
				response.getWriter().write(json.toJson("{\"esito\": \"successo\"}"));
			}else {
				response.getWriter().write(json.toJson("{\"esito\": \"fallimento\"}"));
			}
		}else if(segnalazione.equals("deleteSegnalazione")){
			int id = Integer.parseInt(request.getParameter("id"));
			String oggetto = request.getParameter("oggetto");
			String descrizione = request.getParameter("descrizione");
			String lab = request.getParameter("laboratorio");
			int pos = Integer.parseInt(request.getParameter("postazione"));
			String studente = request.getParameter("studente");
			java.util.Date d = new java.util.Date();
			Date data = new Date(d.getTime());
			Segnalazione s = new Segnalazione(id, oggetto, descrizione, data, studente, lab, pos);
			response.setContentType("application/json");
			if(cm.deleteSegnalazione(s)) {
				response.getWriter().write(json.toJson("{\"esito\": \"successo\"}"));
			}else {
				response.getWriter().write(json.toJson("{\"esito\": \"errore\"}"));
			}
		}else if(segnalazione.equals("viewSegnalazioni")){
			Studente st = (Studente) session.getAttribute("user");
			response.setContentType("application/json");
			int count = 0, i = 0;
			List<Segnalazione> lista = cm.viewSegnalazione();
			String result = "{";
			if(st instanceof Studente){
				while(count < lista.size()){
					if(lista.get(count).getStudente().equals(st.getEmail())){
						result += "\"sg" + i + "\": {\"id\": \"" + lista.get(count).getId() + "\", \"oggetto\": \"" + lista.get(count).getOggetto() + "\", \"descrizione\": \"" + lista.get(count).getDescrizione() + "\", \"data\": \"" + lista.get(count).getData() + "\", \"studente\": \"" + lista.get(count).getStudente() + "\", \"laboratorio\": \"" + lista.get(count).getLaboratorio() + "\", \"postazione\": \"" + lista.get(count).getPostazione() + "\"},";
						count++;
						i++;
					}else {
						count++;
					}
				}
			}else{
				while(count < lista.size()){
					result += "\"sg" + count + "\": {\"id\": \"" + lista.get(count).getId() + "\", \"oggetto\": \"" + lista.get(count).getOggetto() + "\", \"descrizione\": \"" + lista.get(count).getDescrizione() + "\", \"data\": \"" + lista.get(count).getData() + "\", \"studente\": \"" + lista.get(count).getStudente() + "\", \"laboratorio\": \"" + lista.get(count).getLaboratorio() + "\", \"postazione\": \"" + lista.get(count).getPostazione() + "\"},";
					count++;
				}
			}
			result = result.substring(0, result.length() - 1) + "}";
			response.getWriter().write(json.toJson(result));
		}else if(segnalazione.equals("openSegnalazione")){
			int flag = 0, i = 0;
			response.setContentType("application/json");
			response.sendRedirect("./segnalazione.jsp");
			int id = Integer.parseInt(request.getParameter("id"));
			List<Segnalazione> lista = cm.viewSegnalazione();
			while(flag == 0){
				if(lista.get(i).getId() == id) {
					flag++;
				}else{
					i++;
				}
			}
			response.getWriter().write(json.toJson("{\"id\": \"" + lista.get(i).getId() + "\", \"oggetto\": \"" + lista.get(i).getOggetto()) + "\", \"descrizione\": \"" + lista.get(i).getDescrizione() + "\", \"data\": \"" + lista.get(i).getData() + "\", \"laboratorio\": " + lista.get(i).getLaboratorio() + "\", \"postazione\": " + lista.get(i).getPostazione() + "\", \"studente\": " + lista.get(i).getStudente() + "\"}");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		doGet(request, response);
	}
}
