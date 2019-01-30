package presentation.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import businessLogic.laboratorio.LaboratorioManager;
import dataAccess.storage.bean.Laboratorio;

/**
 * Servlet implementation class ServletLaboratorio
 */
@WebServlet("/ServletLaboratorio")
public class ServletLaboratorioManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	LaboratorioManager manager=new LaboratorioManager();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		
		if(action == null){
			response.setStatus(404);
			response.sendRedirect("./index.jsp");
		}else if(action.equals("aggiungi_lab")){//aggiungi laboratorio
			Laboratorio lab=new Laboratorio();
			lab.setNome(request.getParameter("nome"));
			lab.setPosti(Integer.parseInt(request.getParameter("posti")));
			lab.setStato(Boolean.parseBoolean(request.getParameter("stato")));
			
			/*SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			long ms = sdf.parse(request.getParameter("apertura")).getTime();*/
			lab.setApertura(java.sql.Time.valueOf(request.getParameter("apertura")));
			lab.setChiusura(java.sql.Time.valueOf(request.getParameter("chiusura")));
			
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			
			if(manager.createLaboratory(lab)){
				response.getWriter().write("{\"esito\":\"laboratorio creato\"}");
			}else{
				response.getWriter().write("{\"esito\":\"non è possibile creare il laboratorio\"}");
			}
			
		}else if(action.equals("rimuovi_lab")){//rimuovi laboratorio
			Laboratorio lab=new Laboratorio();
			
			lab.setNome(request.getParameter("nome"));
			lab.setPosti(Integer.parseInt(request.getParameter("posti")));
			lab.setStato(Boolean.parseBoolean(request.getParameter("stato")));
			
			/*SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			long ms = sdf.parse(request.getParameter("apertura")).getTime();*/
			lab.setApertura(java.sql.Time.valueOf(request.getParameter("apertura")));
			lab.setChiusura(java.sql.Time.valueOf(request.getParameter("chiusura")));
			
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			
			if(manager.removeLaboratory(lab)){
				response.getWriter().write("{\"esito\":\"laboratorio eliminato\"}");
			}else{
				response.getWriter().write("{\"esito\":\"non è possibile eliminare il laboratorio\"}");
			}
		}else if(action.equals("lista_lab")){//visualizzazione lista laboratori
			//List<Laboratorio> laboratori= manager.getLaboratoryList();
			request.setAttribute("laboratori", manager.getLaboratoryList());
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/adminInterface/visualizzaLaboratori.jsp");
			dispatcher.forward(request, response);
		}
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


}
