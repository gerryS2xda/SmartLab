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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

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
			response.sendRedirect("./errorPage.jsp");
		}else if(action.equals("edit_password")){ 
			
			String email = request.getParameter("email");
			String pwd = request.getParameter("pwd");
			if(email != null && pwd != null && !email.equals("") && !pwd.equals("")){
				try {
					manager.editPassword(email, pwd);
				} catch (SQLException e) {
					e.printStackTrace();
					response.getWriter().write(json.toJson("{\"esito\": false}"));
				}
			}
			response.getWriter().write(json.toJson("{\"esito\": true}"));
			
			//modifica i dati dell'oggetto presente in sessione
			Studente s = (Studente) session.getAttribute("user");
			s.setPassword(pwd);
			session.removeAttribute("user");
			session.setAttribute("user", s);
			
		}else if(action.equals("registraStudente")){
			
			//crea uno studente prendendo i dati dalla richiesta
			Studente s = new Studente();
			s.setEmail(request.getParameter("u_email"));
			s.setPassword(request.getParameter("pwd"));
			s.setName(request.getParameter("u_name"));
			s.setSurname(request.getParameter("u_surname"));
			s.setStato(true);	//true --> account e' attivo; con false --> account sospeso
			
			//verifica che l'utente non sia gia' presente nel DB
			if(manager.isStudentPresent(s.getEmail())){
				session.setAttribute("userstate", "present"); //usato per verificare se utente e' gia presente
				response.sendRedirect("./Login.jsp");
			}else{
	
				//salva dati del nuovo utente nel DB
				try {
					manager.registraStudente(s);
				} catch (SQLException e) {	//da definire cosa fare in caso di errore, es. redirect to errorPage
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				session.removeAttribute("userstate");
				
				/*dopo che i dati del nuovo studente sono stati salvati, posso rimuovere
				l'attr. 'UTProtect' aggiunto per la sicurezza nei confronti della registrazione di un nuovo admin */
				String secure = (String) session.getAttribute("UTProtect");	//protezione
				if(secure != null){
					session.removeAttribute("UTProtect");
				}
				
				//invia l'utente nella login
				response.sendRedirect("./Login.jsp");
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
					response.getWriter().write(json.toJson("{\"esito\":\"sospensione effettuata\"}"));
				} else {
					response.getWriter().write(json.toJson("{\"esito\":\"sospensione fallita\"}"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		} else if(action.equals("getAccountList")) {
			try {
				request.setAttribute("utenti", manager.getStudentList());
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/respInterface/sospendiAccount.jsp");
				dispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(action.equals("logout")){
			
			boolean done = false;
			Utente ut = (Utente) session.getAttribute("user");
			if(ut != null){
				//rimuovi gli utenti dalla sessione
				session.removeAttribute("user");
				session.removeAttribute("userType");
				
				//rimuovi l'attr. 'userstate' se in precedenza e' stato inserito
				String usrState = (String) session.getAttribute("userstate");
				if(usrState != null){
					session.removeAttribute("userstate");
				}
				
				done = true;
			}
			response.getWriter().write(json.toJson("{\"done\":" + done + "}"));
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
