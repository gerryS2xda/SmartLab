package presentation.servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import businessLogic.laboratorio.LaboratorioManager;
import dataAccess.storage.bean.Laboratorio;

/**
 * Servlet implementation class ServletLaboratorio
 */
//@WebServlet("/ServletLaboratorio")
public class ServletLaboratorioManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	LaboratorioManager manager=new LaboratorioManager();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		Gson json = new Gson();
		
		if(action == null){
			response.setStatus(404);
			response.sendRedirect("./index.jsp");
		}else if(action.equals("aggiungi_lab")){//aggiungi laboratorio
			Laboratorio lab=new Laboratorio();
			lab.setNome(request.getParameter("nome"));
			lab.setPosti(Integer.parseInt(request.getParameter("posti")));
			lab.setStato(true);
			
			lab.setApertura(LocalTime.parse(request.getParameter("apertura")));
			lab.setChiusura(LocalTime.parse(request.getParameter("chiusura")));
			System.out.println("nome: "+lab.getNome()+"\nposti: "+lab.getPosti()+"\napertura: "+lab.getApertura());
			
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			
			if(manager.createLaboratory(lab)){
				response.getWriter().write("{\"esito\":\"laboratorio creato\"}");
			}else{
				response.getWriter().write("{\"esito\":\"non è possibile creare il laboratorio\"}");
			}
			
		}else if(action.equals("rimuovi_lab")){//rimuovi laboratorio
			Laboratorio lab=new Laboratorio();
			
			lab.setIDlaboratorio(request.getParameter("idlaboratorio"));
			/*lab.setNome(request.getParameter("nome"));
			lab.setPosti(Integer.parseInt(request.getParameter("posti")));
			lab.setStato(Boolean.parseBoolean(request.getParameter("stato")));*/
			
			/*SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			long ms = sdf.parse(request.getParameter("apertura")).getTime();*/
			/*lab.setApertura(java.sql.Time.valueOf(request.getParameter("apertura")));
			lab.setChiusura(java.sql.Time.valueOf(request.getParameter("chiusura")));*/
			
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			System.out.println(lab.getIDlaboratorio());
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
		}else if(action.equals("lista_lab_attivi")){//visualizzazione lista laboratori responsaile
			//List<Laboratorio> laboratori= manager.getLaboratoryList();
			String email="esempio1@unisa.it";
			request.setAttribute("laboratori", manager.getLaboratoryListForResp(email));
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/respInterface/laboratoriAssegnati.jsp");
			dispatcher.forward(request, response);
		}else if(action.equals("lab_attivi")){
			
			//costruisci risposta JSON
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			
			List<Laboratorio> laboratori= manager.getLaboratoryList();
			String str = "{";
			for(int i = 0; i < laboratori.size(); i++){
				Laboratorio lab = laboratori.get(i);
				str +=  "\"lab" + i + "\": {\"IDlaboratorio\":" + lab.getIDlaboratorio() + ", \"nome\": \"" + lab.getNome() + "\", " + 
				"\"posti\": " + lab.getPosti() + ", \"stato\": " + lab.isStato() + ",  \"apertura\": \"" + lab.getApertura().toString() + "\", " + 
				"\"chiusura\": \"" + lab.getChiusura().toString() + "\" },";
			}
			str = str.substring(0, str.length() - 1) + "}"; //rimuovi ultima ',' e poi aggiungi '}'
			response.getWriter().write(json.toJson(str));
		}
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
