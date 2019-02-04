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

import com.google.gson.Gson;

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
		Gson json = new Gson();
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String action=request.getParameter("action");
		
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
			String s;
			boolean flag;
			//setta lo stato di postazione a true
			String idlab=request.getParameter("idlab");
			String idpos=request.getParameter("id");
		
				flag=pm.attivaPostazione(idpos, idlab);
		
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			
			if(flag)
			{
				s="{\"esito\":\"stato modificato\" }";
			}else 
			{
				s="{\"esito\":\"stato non modificato\" }";
			}
			
			response.getWriter().write(s);
		}
		
		else if(action.equals("disattiva_pos"))
		{
			
			boolean flag;
			String s;
			String idlab=request.getParameter("idlab");
			String idpos=request.getParameter("id");
			
			//setta lo stato di postazione a false
			flag=pm.disattivaPostazione(idpos, idlab);
			
//			request.getRequestDispatcher("lista_postazioni.jsp").forward(request,response);
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			
			
			if(flag)
			{
				s="{\"esito\":\"stato modificato\" }";
			}else 
			{
				s="{\"esito\":\"stato non modificato\" }";
			}
			
			response.getWriter().write(s);
		}
				
		else if(action.equals("lista_pos"))
		{
			System.out.println("lista");
			String idlab =(String) request.getParameter("idlaboratorio");
			
			List<Postazione> lp=pm.listaPostazioni(idlab);
			
			//mandare alla jsp
			request.setAttribute("lista", lp);
			getServletContext().getRequestDispatcher("/respInterface/lista_postazioni.jsp").forward(request,response);

		}else if(action.equals("lista_pos_json")){	//usato per la PrenotazionePage
			
			//costruisci risposta JSON
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			
			String idlab = request.getParameter("id_lab");
			List<Postazione> postazioni = pm.listaPostazioni(idlab);
			
			String str = "{";
			for(int i = 0; i < postazioni.size()-1; i++){
				Postazione p = postazioni.get(i);
				str +=  "\"post" + i + "\": {\"numero\":" + p.getNumero() + ", \"labID\": " + p.getLaboratorio() + ", " + 
				"\"stato\": " + p.isStato() + " },";
			}
			str = str.substring(0, str.length() - 1) + "}"; //rimuovi ultima ',' e poi aggiungi '}'
			response.getWriter().write(json.toJson(str));
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
