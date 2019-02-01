package presentation.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import businessLogic.utente.UtenteManager;
import dataAccess.storage.bean.Sospensione;
import dataAccess.storage.bean.Studente;

/**
 * Servlet implementation class ServletUtenteManagement
 */
@WebServlet("/ServletUtente")
public class ServletUtenteManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    UtenteManager manager = new UtenteManager();
    

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
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
			
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			
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
			
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			
			try {
				if(manager.effettuaAutenticazione(email, password)){
					response.getWriter().write("{\"esito\":\"autenticazione effettuata\"}");
				} else {
					response.getWriter().write("{\"esito\":\"autenticazione fallita\"}");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if(action.equals("effettuaSospensione")){
			Studente s = new Studente();
			s.setEmail(request.getParameter("emailStud"));
			
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			
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
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/respInterface/sospendiAccountjsp");
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
