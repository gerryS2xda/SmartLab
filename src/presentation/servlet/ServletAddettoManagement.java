package presentation.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import businessLogic.addetto.AddettoManager;
import dataAccess.storage.bean.Addetto;

public class ServletAddettoManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private AddettoManager manager = new AddettoManager();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		if(action == null){
			response.setStatus(404);
			response.sendRedirect("./index.jsp");
			
		} else if(action.equals("addResp")){
			Addetto a = new Addetto();
			a.setEmail(request.getParameter("email"));
			a.setPassword(request.getParameter("password"));
			a.setName(request.getParameter("nome"));
			a.setSurname(request.getParameter("cognome"));
			a.setTipo(false);
			
			System.out.println("email: "+a.getEmail()+"\npassword: "+a.getPassword()+""
					+ "\nnome: "+a.getName()+"\ncognome: "+a.getSurname()+"\nstato: "+a.getTipo());
			
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			
			try {
				if(manager.addResp(a)){
					response.getWriter().write("{\"esito\":\"addetto registrato\"}");
				}
			} catch (SQLException e) {
				response.getWriter().write("{\"esito\":\"addetto non registrato\"}");
				e.printStackTrace();
			}
			
		} else if(action.equals("rimuoviResp")){
			String email = request.getParameter("email");
			Addetto a = new Addetto();
			a.setEmail(email);
			a.setTipo(false);
			
			if(manager.rimuoviResp(a)){
				response.getWriter().write("{\"esito\":\"addetto eliminato\"}");
			} else {
				response.getWriter().write("{\"esito\":\"addetto non eliminato\"}");
			}
		} else if(action.equals("getListResp")){
			try {
				request.setAttribute("responsabili", manager.getListaResp());
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/adminInterface/visualizzaResp.jsp");
				dispatcher.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
