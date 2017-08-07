package servlet.view;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Account;
import bean.ActReg;
import database.ActRegDB;
import database.ActivityDB;
import database.ForumDB;

/**
 * Servlet implementation class MyProfile
 */
@WebServlet("/MyProfile")
public class MyProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ss = request.getSession(true);
		if(ss.getAttribute("account") != null) {
			ForumDB fdb = new ForumDB();
			ActRegDB ardb = new ActRegDB();
			ActivityDB adb = new ActivityDB();
			
			Account ac = (Account) ss.getAttribute("account");
			ArrayList<bean.Post> post = fdb.getUserPost(ac.getAccountId());
			request.setAttribute("post", post);
			ArrayList<bean.ActReg> ar = ardb.getRegistrationById(ac.getAccountId());
			request.setAttribute("ar", ar);
			request.getRequestDispatcher("pages/profile.jsp").forward(request, response);
		}else {
			response.sendRedirect("Index");
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
