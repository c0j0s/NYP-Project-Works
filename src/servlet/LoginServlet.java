package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Account;
import database.AccountDB;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String userId=request.getParameter("email");
		String userPw=request.getParameter("userPw");
		try{
			AccountDB myDatabase = new AccountDB();
			Account ac = myDatabase.isMember(userId, userPw);
			if(ac!=null){

				System.out.println("Log loginservlet: success");
				HttpSession mySession = request.getSession(true);
				mySession.setAttribute("account", ac);
				request.getRequestDispatcher("pages/profile.jsp").forward(request, response);
			}
			else{

				System.out.println("Log loginservlet: fail");
				request.getRequestDispatcher("pages/login.jsp").forward(request, response);
			}
		}catch(Exception ex){
			System.out.println("Error Accessing database: "+ex);
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
