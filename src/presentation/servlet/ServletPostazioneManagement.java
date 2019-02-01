package presentation.servlet;

import java.io.IOException;
import java.sql.SQLException;

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
@WebServlet("/ServletPostazione")
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
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String action=request.getParameter("action");
		
		PostazioneManager pm=new PostazioneManager();
		
		//PostazioneSql psql=new PostazioneSql();
		Postazione pos=new Postazione();
		Laboratorio lab=new Laboratorio();
		Prenotazione pre=new Prenotazione();
		
		
		if(action == null)
		{
			response.setStatus(404);
			response.sendRedirect("./index.jsp");
		}
		else if(action.equals("libera_pos"))
		{ 
			
		
		
		
		
		pm.liberaPostazione(pre);//setta lo stato di postazione a false
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		}
		
		else if(action.equals("attiva_pos"))
		{
			LaboratorioSql labsql=new LaboratorioSql(request.getParameter("laboratorio"));//mi ricavo un laboratorio
			pos.setNumero(Integer.parseInt(request.getParameter("numero")));
			
			try {
				pos.setLaboratorio(labr.findItemByQuery(labsql));//setto un laboratorio
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			
			pos.setStato(Boolean.parseBoolean(request.getParameter("stato")));
			pm.attivaPostazione(pos);//setta lo stato di postazione a true
			
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
		}
		else if(action.equals("lista_pos"))
		{
			String i =request.getParameter("postazione");
			
			response.sendRedirect("/SmartLab/lista_postazioni");

		}
		
	}


}
