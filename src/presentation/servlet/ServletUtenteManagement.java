package presentation.servlet;

import businessLogic.addetto.AddettoManager;
import businessLogic.utente.UtenteManager;
import com.google.gson.Gson;
import dataAccess.storage.bean.Addetto;
import dataAccess.storage.bean.Sospensione;
import dataAccess.storage.bean.Studente;
import dataAccess.storage.bean.Utente;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;


public class ServletUtenteManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;    

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	HttpSession session = request.getSession();
    	UtenteManager manager = new UtenteManager();
    	AddettoManager managerAddetto = new AddettoManager();
    	String action = request.getParameter("action");
    	Gson json = new Gson();
    	
    	response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
    	if(action == null){
			response.setStatus(404);
			response.sendRedirect("./errorPage.jsp");
			
		}else if(action.equals("editPassword")){ 
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			if(email != null && password != null && !email.equals("") && !password.equals("")){
				try {
					manager.editPassword(email, password);
				} catch (SQLException e) {
					e.printStackTrace();
					response.getWriter().write(json.toJson("{\"esito\": false}"));
				}
			}
			response.getWriter().write(json.toJson("{\"esito\": true}"));
			
			Studente s = (Studente) session.getAttribute("user");
			s.setPassword(password);
			session.removeAttribute("user");
			session.setAttribute("user", s);
			
		}else if(action.equals("registraStudente")){
			
			Studente s = new Studente();
			s.setEmail(request.getParameter("s_email"));
			s.setPassword(request.getParameter("password"));
			s.setName(request.getParameter("s_name"));
			s.setSurname(request.getParameter("s_surname"));
			s.setStato(true); //Account attivo
			
			/**Verifica se l'utente è già registrato **/
			if(manager.isStudentPresente(s.getEmail())){
				session.setAttribute("userstate", "presente");
				response.sendRedirect("./Login.jsp");
			}else{
				try {
					manager.registraStudente(s);
				} catch (SQLException e) {
					/** COSA FACCIAMO IN CASO DI ERRORE? **/
					e.printStackTrace();
				}
				
				/**Dopo aver salvato i dati del nuovo studente, viene rimosso l'attributo 'UTProtect'
				 * UTProtect: attributo per la sicurezza nei confronti della registrazione di un nuovo admin **/
				String secure = (String) session.getAttribute("UTProtect");
				if(secure != null){
					session.removeAttribute("UTProtect");
				}
				
				response.sendRedirect("./Login.jsp");
			}
			
		} else if(action.equals("effettuaAutenticazione")){
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			Addetto a = new Addetto();
			Studente s = new Studente();
			Utente u = (Utente) session.getAttribute("user");
			
			if(u == null){
				a = managerAddetto.effettuaAutenticazione(email, password);
				if(a.getEmail().equals("")){
					s = manager.effettuaAutenticazione(email, password);
					if(s.getEmail().equals("")){
						response.getWriter().write(json.toJson("{\"userstate\": \"errLogin\", \"userType\": \"null\"}"));
					}else{
						session.setAttribute("user", s);
						session.setAttribute("userType", "studente");
						response.getWriter().write(json.toJson("{\"userstate\": \"ok\", \"userType\":\"studente\"}"));
					}
				}else{
					session.setAttribute("user", a);
					if(a.getTipo()){
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
			s.setEmail(request.getParameter("emailStudente"));
			String motivazione = (String) request.getParameter("motivazione");
			try{
				Sospensione v = manager.effettuaSospensione(s, motivazione);
				if(v.getStudente()!= null && s.getStato() == true){
					response.getWriter().write("{\"esito\":\"sospensione effettuata\"}");
				} else {
					response.getWriter().write("{\"esito\":\"sospensione non effettuata\"}");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		} else if(action.equals("getStudentList")) {
			try {
				request.setAttribute("studenti", manager.getStudentList());
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/respInterface/sospendiAccount.jsp");
				dispatcher.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if(action.equals("logout")){
			
			boolean done = false;
			Utente u = (Utente) session.getAttribute("user");
			if(u != null){
				session.removeAttribute("user");
				session.removeAttribute("userType");
				
				String userState = (String) session.getAttribute("userstate");
				if(userState != null){
					session.removeAttribute("userstate");
				}
				
				done = true;
			}
			response.getWriter().write(json.toJson("{\"done\":" + done + "}"));
		}		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
