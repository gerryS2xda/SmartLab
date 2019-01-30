package presentation.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import businessLogic.assegnamento.AssegnamentoManager;
import dataAccess.storage.bean.Assegnamento;

/**
 * Servlet implementation class ServletAssegnamentoManagement
 */
@WebServlet("/ServletAssegnamentoManagement")
public class ServletAssegnamentoManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AssegnamentoManager manager=new AssegnamentoManager();
		String action=request.getParameter("action");
		
		if(action == null){
			response.setStatus(404);
			response.sendRedirect("./index.jsp");
		}else if(action.equals("aggiungi_ass")){//assegna un laboratorio a un responsabile
			Assegnamento as=new Assegnamento();
			
			as.setLaboratorio(request.getParameter("idlaboratorio"));
			as.setResponsabile(request.getParameter("idresponsabile"));
			
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			
			if(manager.setRespToLab(as)){
				response.getWriter().write("{\"esito\":\"laboratorio assegnato\"}");
			}else{
				response.getWriter().write("{\"esito\":\"non è stato possibile assegnare il laboratorio\"}");
			}
			
		}else if(action.equals("rimuovi_ass")){//rimuove un responsabile da un laboratorio
			Assegnamento as=new Assegnamento();
			
			as.setLaboratorio(request.getParameter("idlaboratorio"));
			as.setResponsabile(request.getParameter("idresponsabile"));
			
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			
			if(manager.removeResponsabile(as)){
				response.getWriter().write("{\"esito\":\"responsabile rimosso dal laboratorio\"}");
			}else{
				response.getWriter().write("{\"esito\":\"non è stato possibile rimuovere il responsabile dal laboratorio\"}");
			}
		}
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}



}
