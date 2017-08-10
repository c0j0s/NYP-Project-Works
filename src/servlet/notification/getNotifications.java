package servlet.notification;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import bean.Account;
import bean.Notification;
import database.NotificationDB;

/**
 * Servlet implementation class getNotifications
 */
@WebServlet("/getNotifications")
public class getNotifications extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getNotifications() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ss = request.getSession();
		String type = request.getParameter("type");
		if(ss.getAttribute("account") != null) {
			NotificationDB ndb = new NotificationDB();
			Account ac = (Account) ss.getAttribute("account");
			ArrayList<Notification> list = null;
			if(type.equals("unread")) {
				list = ndb.getAccountUnNotifications(ac.getAccountId());
			}else if(type.equals("all")){
				list = ndb.getAccountNotifications(null,ac.getAccountId());
			}
			String json = new Gson().toJson(list);
			response.getWriter().append(json);
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
