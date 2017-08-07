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
 * Servlet implementation class ResetPwServlet2
 */
@WebServlet("/ResetPwServlet2")
public class ResetPwServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetPwServlet2() {
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
		//doGet(request, response);
		HttpSession mySession = request.getSession(true);
		
		if(mySession.getAttribute("account") != null){
			Account ac = (Account)mySession.getAttribute("account");
			ac.getEmail();
			String pw = request.getParameter("pw");
			String cpw = request.getParameter("cpw");
			if(pw.equals(cpw)){
				AccountDB ac1 = new AccountDB();
				ac1.resetPw(pw, ac.getEmail());System.out.println(ac.getEmail());
				request.getRequestDispatcher("MyProfile").forward(request, response);
			}else{
				System.out.println("Password not matched.");
			}
			
		}else{
			request.getRequestDispatcher("LogoutServlet").forward(request,response);
		}
		
	}

}
