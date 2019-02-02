package presentation.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import businessLogic.addetto.AddettoManager;
import businessLogic.utente.UtenteManager;
import dataAccess.storage.bean.Addetto;
import dataAccess.storage.bean.Sospensione;
import dataAccess.storage.bean.Studente;
import dataAccess.storage.bean.Utente;

/**
 * Servlet implementation class ServletUtenteManagement
 */
@WebServlet("/ServletUtente")
public class ServletUtenteManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;    

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	HttpSession session = request.getSession();
    	UtenteManager manager = new UtenteManager();
    	AddettoManager managerAddetto = new AddettoManager();
    	String action = request.getParameter("action");
    	Gson json = new Gson();
    	
    	//imposta le risposte come stringhe JSON per tutta la servlet
    	response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
    	if(action == null){
			response.setStatus(404);
			response.sendRedirect("./index.jsp");
		} else if(action.equals("registraStudente")){
			Studente s = new Studente();
			s.setEmail(request.getParameter("email"));
			s.setPassword(request.getParameter("password"));
			s.setName(request.getParameter("nome"));
			s.setSurname(request.getParameter("cognome"));
			s.setStato(false);
			
			System.out.println("email: "+s.getEmail()+"\npassword: "+s.getPassword()+""
					+ "\nnome: "+s.getName()+"\ncognome: "+s.getSurname()+"\nstato: "+s.getStato());
			
			
			try {
				if(manager.registraStudente(s)){
					response.getWriter().write("{\"esito\":\"studente registrato\"}");
				} else {
					response.getWriter().write("{\"esito\":\"studente non registrato\"}");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(action.equals("effettuaAutenticazione")){
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			Addetto ad = new Addetto();
			Studente stud = new Studente();
			Utente ut = (Utente) session.getAttribute("user");
			
			if(ut == null){ //aggiungi un utente nella sessione
				//verifica se e' un addetto
				ad = managerAddetto.effettuaAutenticazione(email, password);
				if(ad.getEmail().equals("")){	//non e' un addetto, prova con student
					stud = manager.effettuaAutenticazione(email, password);
					if(stud.getEmail().equals("")){
						//nessuna corrispondenza, valori inseriti non validi
						response.getWriter().write(json.toJson("{\"userstate\": \"errLogin\", \"userType\": \"null\"}"));
					}else{
						//aggiungi lo studente nella sessione
						session.setAttribute("user", stud);
						session.setAttribute("userType", "studente");
						response.getWriter().write(json.toJson("{\"userstate\": \"ok\", \"userType\":\"studente\"}"));
					}
				}else{
					//aggiungi l'addetto nella sessione
					session.setAttribute("user", ad);
					if(ad.getTipo()){
						session.setAttribute("userType", "admin");
						response.getWriter().write(json.toJson("{\"userstate\": \"ok\", \"userType\":\"admin\"}"));
					}else{
						session.setAttribute("userType", "responsabile");
						response.getWriter().write(json.toJson("{\"userstate\": \"ok\", \"userType\":\"responsabile\"}"));
					}
				}
			}else{
				response.getWriter().write(json.toJson("{\"userstate\": \"logged\", \"userType\": \"null\"}"));
			}
			
		} else if(action.equals("effettuaSospensione")){
			Studente s = new Studente();
			s.setEmail(request.getParameter("emailStud"));
			
			try{
				Sospensione v = manager.effettuaSospensione(s);
				if(v.getStudente()!= null && s.getStato() == true){
					response.getWriter().write("{\"esito\":\"sospensione effettuata\"}");
				} else {
					response.getWriter().write("{\"esito\":\"sospensione fallita\"}");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		} else if(action.equals("getAccountList")) {
			try {
				request.setAttribute("utenti", manager.getAccountList());
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/respInterface/sospendiAccount.jsp");
				dispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
				
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
