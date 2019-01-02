package presentation.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import businessLogic.prenotazione.PrenotazioneManager;

@WebServlet("/prenotazione-serv")
public class ServletPrenotazioneManagement extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrenotazioneManager manager = new PrenotazioneManager();
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
			String fascia_or = request.getParameter("fascia_or");
			
			manager.effettuaPrenotazione(stud, post, fascia_or);
			
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
			String fascia_or = request.getParameter("fascia_or");
			
			//collegarsi con la repository di postazione per ottenere un oggetto Postazione da cui vedere se e' disponibile
			
			response.getWriter().write(json.toJson("{\"esito\": \"ok\"}"));
			boolean statusPost = true;
			if(statusPost){
				response.getWriter().write(json.toJson("{\"status\": \"disponibile\"}"));
			}else{
				response.getWriter().write(json.toJson("{\"status\": \"occupata\"}"));
			}
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
