package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.ActivityDB;

/**
 * Servlet implementation class DeleteActivity
 */
@SuppressWarnings("unused")
@WebServlet("/DeleteActivity")
public class DeleteActivity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteActivity() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ActivityDB actdb = new ActivityDB();
		String check = request.getParameter("action");
		String actId =(request.getParameter("actId"));
		
		if (check.equalsIgnoreCase("Invalid")) {
		actdb.deleteActivity(actId);
		response.getWriter().append("N");
		} else {
			actdb.restoreActivity(actId);
			response.getWriter().append("Y");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
