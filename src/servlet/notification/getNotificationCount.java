package servlet.notification;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Account;
import database.NotificationDB;

/**
 * Servlet implementation class getNotificationCount
 */
@WebServlet("/getNotificationCount")
public class getNotificationCount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getNotificationCount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NotificationDB ndb = new NotificationDB();
		HttpSession ss = request.getSession();
		if(ss.getAttribute("account") != null) {
			Account ac = (Account) ss.getAttribute("account");
			int count = ndb.getNotificationCount(ac.getAccountId());
			request.setAttribute("notificationCount", count);
			response.getWriter().append(Integer.toString(count));
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
