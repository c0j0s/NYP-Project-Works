package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.AccountDB;

/**
 * Servlet implementation class ResetPwServlet
 */
@WebServlet("/ResetPwServlet")
public class ResetPwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetPwServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		HttpSession mySession = request.getSession(true);
		String id = request.getParameter("id");
		String email = (String) mySession.getAttribute(id);
		String pw = request.getParameter("pw");
		String cpw = request.getParameter("cpw");System.out.println(pw);System.out.println(cpw);
		if(pw.equals(cpw)){
			AccountDB ac = new AccountDB();
			ac.resetPw(pw, email);
			request.getRequestDispatcher("pages/login.jsp").forward(request, response);
		}else{
			System.out.println("Password not matched.");
		}
	}

}
