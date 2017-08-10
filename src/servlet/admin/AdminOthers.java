package servlet.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Account;
import bean.Notification;
import database.ForumDB;
import database.NotificationDB;

/**
 * Servlet implementation class AdminOthers
 */
@WebServlet("/AdminOthers")
public class AdminOthers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminOthers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ss = request.getSession(false);
		if(ss.getAttribute("account") != null) {
			Account ac = (Account) ss.getAttribute("account");
			String type = request.getParameter("type");
			if(type.equals("message")) {
				NotificationDB ndb = new NotificationDB();
				Notification no = new Notification();
				no.setTitle(request.getParameter("title"));
				no.setMessage(request.getParameter("message"));
				no.setServiceType("FFL Admin");
				no.setAccountId(ac.getAccountId());
				ArrayList<String> accountIds = ndb.getAllUserAccounts();
				ndb.sentNotificationToAccounts(accountIds, no);
				response.getWriter().append("Send message to all users successfully!");
			}else if(type.equals("category")){
				ForumDB fdb = new ForumDB();
				fdb.addCategory(request.getParameter("categoryName"));
				response.getWriter().append("Add forum category successfully!");
			}
		}else {
			response.getWriter().append("Fail to connect to server.");
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
