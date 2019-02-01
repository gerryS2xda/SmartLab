package presentation.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.*;

import businessLogic.comunicazione.CommunicationManager;
import dataAccess.storage.bean.Avviso;

public class ServletAvviso extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Gson json = new Gson();
		String avviso = request.getParameter("action");
		CommunicationManager cm = new CommunicationManager();
		if(avviso == null){
			response.setStatus(404);
			response.sendRedirect("./Errore.jsp");
		}else if(avviso.equals("newAvviso")){
			List<Avviso> lista = cm.viewAvviso();
			int count = 0, id = -1;
			while(count < lista.size()){
				if(lista.get(count).getId() >= id)
					id = lista.get(count).getId();
				count++;
			}
			String titolo = request.getParameter("titolo");
			String messaggio = request.getParameter("messaggio");
			java.util.Date d = new java.util.Date();
			Date data = new Date(d.getTime());
			String addetto = request.getParameter("addetto");
			Avviso a = new Avviso(id, titolo, messaggio, data, addetto);
			cm.addAvviso(a);
			response.setContentType("application/json");
			response.getWriter().write("{\"esito\": \"avviso creato\"}");
		}else if(avviso.equals("deleteAvviso")){
			int id = Integer.parseInt(request.getParameter("id"));
			String titolo = request.getParameter("titolo");
			String messaggio = request.getParameter("messaggio");
			java.util.Date d = new java.util.Date();
			Date data = new Date(d.getTime());
			String addetto = request.getParameter("addetto");
			Avviso a = new Avviso(id, titolo, messaggio, data, addetto);
			cm.deleteAvviso(a);
			response.setContentType("application/json");
			response.getWriter().write("{\"esito\": \"successo\"}");
		}else if(avviso.equals("viewAvvisi")){
			response.setContentType("application/json");
			int count = 0;
			List<Avviso> lista = cm.viewAvviso();
			String result = "{";
			while(count < lista.size()){
				result += "\"av" + count + "\": {\"id\": \"" + lista.get(count).getId() + "\", \"titolo\": \"" + lista.get(count).getTitolo() + "\", \"messaggio\": \"" + lista.get(count).getMessaggio() + "\", \"data\": \"" + lista.get(count).getData() + "\", \"addetto\": \"" + lista.get(count).getAddetto() + "\"}, ";
				count++;
			}
			result = result.substring(0, result.length() - 1) + "}";
			response.getWriter().write(json.toJson(result));
		}else if(avviso.equals("openAvviso")){
			int flag = 0, i = 0;
			response.setContentType("application/json");
			response.sendRedirect("./avviso.jsp");
			int id = Integer.parseInt(request.getParameter("id"));
			List<Avviso> lista = cm.viewAvviso();
			while(flag == 0){
				if(lista.get(i).getId() == id)
					flag++;
				else
					i++;
			}
			response.getWriter().write(json.toJson("{\"id\": \"" + lista.get(i).getId() + "\", \"titolo\": \"" + lista.get(i).getTitolo() + "\", \"messaggio\": \"" + lista.get(i).getMessaggio() + "\", \"data\": \"" + lista.get(i).getData() + "\", \"addetto\": \"" + lista.get(i).getAddetto() + "\"}"));
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doGet(request, response);
	}
}
