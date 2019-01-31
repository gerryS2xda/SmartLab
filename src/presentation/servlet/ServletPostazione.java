package presentation.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletPostazione
 */
@WebServlet("/ServletPostazione")
public class ServletPostazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPostazione() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String action=request.getParameter("action");
		
		if(action == null)
		{
			response.setStatus(404);
			response.sendRedirect("./index.jsp");
		}
		else if(action.equals("crea_pos"))
			{
				
			}
			else if(action.equals("elimina_pos"))
			{
				
			}
		
	}


}
