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
import database.DBAO;

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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		String userId=request.getParameter("userId");
		String userPw=request.getParameter("userPw");
		
		try{
			DBAO myDatabase = new DBAO();
			Account myAccount = myDatabase.isMember(userId, userPw);
			if(myAccount!=null){
				HttpSession mySession = request.getSession(true);
				mySession.getAttribute("Account");
				request.getRequestDispatcher("").forward(request, response);
			}
			else{
				request.getRequestDispatcher("Login.jsp").forward(request, response);
			}
		}catch(Exception ex){
			System.out.println("Error Accessing Database:" + ex);
		}
	}

}
