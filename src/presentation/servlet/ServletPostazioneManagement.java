package presentation.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import businessLogic.Postazione.PostazioneManager;
import businessLogic.Postazione.PostazioneRepository;
import businessLogic.Postazione.PostazioneSql;
import businessLogic.laboratorio.LaboratorioRepository;
import businessLogic.laboratorio.LaboratorioSql;
import dataAccess.storage.bean.Laboratorio;
import dataAccess.storage.bean.Postazione;
import dataAccess.storage.bean.Prenotazione;

/**
 * Servlet implementation class ServletPostazione
 */
//@WebServlet("/ServletPostazione")
public class ServletPostazioneManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPostazioneManagement() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String action=request.getParameter("action");
		//prova
		//action="lista_pos";
		
		PostazioneManager pm=new PostazioneManager();
		
		//PostazioneSql psql=new PostazioneSql();
		Postazione pos=new Postazione();
		Laboratorio lab=new Laboratorio();
		Prenotazione pre=new Prenotazione();
		
		
		if(action == null)
		{
			response.setStatus(404);
			response.sendRedirect("./Index.jsp");
		}
		else if(action.equals("libera_pos"))   //libera la postazione
		{ 
			
		List<Prenotazione> lista=new ArrayList();
		pm.listaPrenotazioni(request.getParameter("inizio") ,request.getParameter("fine") ,request.getParameter("lab"));
			
		try 
		{
			pm.liberaPostazione(pre);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		//mandare alla jsp
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		}
		
		else if(action.equals("attiva_pos"))
		{
			pos.setStato(true);
			pm.attivaPostazione(pos);                   //setta lo stato di postazione a true
			
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
		}
		
		else if(action.equals("disattiva_pos"))
		{
			
			
			pm.attivaPostazione(pos);                    //setta lo stato di postazione a false
			
			request.setAttribute("postazione",pos);
			request.getRequestDispatcher("lista_postazioni.jsp").forward(request,response);
			//response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
		}
				
		else if(action.equals("lista_pos"))
		{
			
			//String id =(String) request.getParameter("idlaboratorio");
			String id="lab1";
			List<Postazione> lp=pm.listaPostazioni(id);
			
			//mandare alla jsp
			request.setAttribute("lista", lp);
			request.getRequestDispatcher("lista_postazioni.jsp").forward(request,response);
			response.sendRedirect("/SmartLab/lista_postazioni");

		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


}
