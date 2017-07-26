package servlet.notification;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Account;
import database.NotificationDB;

/**
 * Servlet implementation class setNotificationRead
 */
@WebServlet("/setNotificationRead")
public class setNotificationRead extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public setNotificationRead() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		NotificationDB ndb = new NotificationDB();
		HttpSession ss = request.getSession();
		if(ss.getAttribute("account") != null) {
			Account ac = (Account) ss.getAttribute("account");
			ndb.setRead(ac.getAccountId(),id);
		}
		request.getRequestDispatcher("getNotificationCount").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
