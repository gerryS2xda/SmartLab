package presentation.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.*;

import businessLogic.comunicazione.CommunicationManager;
import dataAccess.storage.bean.Addetto;
import dataAccess.storage.bean.Avviso;
import dataAccess.storage.bean.Studente;

@WebServlet("/ServletAvviso")
public class ServletAvviso extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		Gson json = new Gson();
		String avviso = request.getParameter("action");
		CommunicationManager cm = new CommunicationManager();
		if(avviso == null){
			response.setStatus(404);
			response.sendRedirect("./Errore.jsp");
		}else if(avviso.equals("newAvviso")){
			Studente st = (Studente) session.getAttribute("user");
			if(st instanceof Studente){
				response.setContentType("application/json");
				response.getWriter().write(json.toJson("{\"esito\": \"errore\"}"));
			}else{
				List<Avviso> lista = cm.viewAvviso();
				int count = 0, id = -1;
				while(count < lista.size()){
					if(lista.get(count).getId() > id)
						id = lista.get(count).getId();
					count++;
				}
				id++;
				Addetto ad = (Addetto) session.getAttribute("user");
				String titolo = request.getParameter("titolo");
				String messaggio = request.getParameter("messaggio");
				java.util.Date d = new java.util.Date();
				Date data = new Date(d.getTime());
				String addetto = ad.getEmail();
				Avviso a = new Avviso(id, titolo, messaggio, data, addetto);
				response.setContentType("application/json");
				if(cm.addAvviso(a))
					response.getWriter().write("{\"esito\": \"avviso creato\"}");
				else
					response.getWriter().write("{\"esito\": \"avviso non creato\"}");
			}
		}else if(avviso.equals("deleteAvviso")){
			response.setContentType("application/json");
			int id = Integer.parseInt(request.getParameter("id"));
			Avviso a = new Avviso();
			a.setId(id);
			if(cm.deleteAvviso(a))
				response.getWriter().write(json.toJson("{\"esito\": \"successo\"}"));
			else
				response.getWriter().write(json.toJson("{\"esito\": \"errore\"}"));
		}else if(avviso.equals("viewAvvisi")){
			response.setContentType("application/json");
			int count = 0;
			List<Avviso> lista = cm.viewAvviso();
			String result = "{";
			while(count < lista.size()){
				result += "\"av" + count + "\": {\"id\": \"" + lista.get(count).getId() + "\", \"titolo\": \"" + lista.get(count).getTitolo() + "\", \"messaggio\": \"" + lista.get(count).getMessaggio() + "\", \"data\": \"" + lista.get(count).getData() + "\", \"addetto\": \"" + lista.get(count).getAddetto() + "\"},";
				count++;
			}
			result = result.substring(0, result.length() - 1) + "}";
			response.getWriter().write(json.toJson(result));
		}else if(avviso.equals("openAvviso")){
			Studente st = (Studente) session.getAttribute("user");
			String tipo;
			if(st instanceof Studente){
				tipo = "studente";
			}else
				tipo = "addetto";
			int flag = 0, i = 0;
			response.setContentType("application/json");
			int id = Integer.parseInt(request.getParameter("id"));
			List<Avviso> lista = cm.viewAvviso();
			while(flag == 0 && i < lista.size()){
				if(lista.get(i).getId() == id)
					flag++;
				else
					i++;
			}
			if(flag != 0){
				response.getWriter().write(json.toJson("{\"id\": \"" + lista.get(i).getId() + "\", \"titolo\": \"" + lista.get(i).getTitolo() + "\", \"messaggio\": \"" + lista.get(i).getMessaggio() + "\", \"data\": \"" + lista.get(i).getData() + "\", \"addetto\": \"" + lista.get(i).getAddetto() + "\", \"tipo\": \"" + tipo + "\"}"));
			}else
				response.getWriter().write(json.toJson("{\"esito\": \"errore\"}"));
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doGet(request, response);
	}
}
