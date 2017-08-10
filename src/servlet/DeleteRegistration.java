package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Account;
import database.ActRegDB;
import database.ActivityDB;

/**
 * Servlet implementation class DeleteRegistration
 */
@WebServlet("/DeleteRegistration")
public class DeleteRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteRegistration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ActRegDB ardb = new ActRegDB();
		ActivityDB adb = new ActivityDB();
		Account ac = (Account) request.getSession().getAttribute("account");
		 String userId = ac.getAccountId();
		 String registrationId = request.getParameter("registrationId");
		 ardb.deleteRegistration(userId, registrationId);
String actId = request.getParameter("activityId");
			adb.editParNo(actId, -1);
		 response.sendRedirect("MyProfile");
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
