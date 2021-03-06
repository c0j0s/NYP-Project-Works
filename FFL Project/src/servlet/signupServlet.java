package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Account;
import common.UID;
import database.AccountDB;

/**
 * Servlet implementation class signupServlet
 */
@WebServlet("/signupServlet")
public class signupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public signupServlet() {
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
		Account ac = new Account();
		
		ac.setAccountId(UID.genAccountId());
		ac.setGivenName(request.getParameter("firstName"));
		ac.setSurName(request.getParameter("lastName"));
		ac.setDob(java.sql.Date.valueOf(request.getParameter("dob")));
		ac.setGender(request.getParameter("gender").charAt(0));
		ac.setEmail(request.getParameter("email"));
		ac.setAddress(request.getParameter("address"));
		ac.setMobileno(Integer.parseInt(request.getParameter("mobileno")));
		String imgurl = request.getParameter("imgurl");
		ac.setImgUrl(imgurl);
		ac.setPoints(100);
		String pw = request.getParameter("pw");
		String cpw = request.getParameter("cpw");
		if(!pw.equals(cpw)){
			request.getRequestDispatcher("signup.jsp").forward(request, response);
		}
		else{
			try{ 
				AccountDB ac1 = new AccountDB();
				ac1.regMember(ac, cpw);
				HttpSession mySession = request.getSession(true);
				mySession.setAttribute("account", ac);
				request.getRequestDispatcher("MyProfile").forward(request, response);
			}catch(Exception ex){
				System.out.println(ex.getMessage());
			}
		}
	}

}
