package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import com.sun.mail.util.MailSSLSocketFactory;

import bean.Account;
import common.MailSSL;
import common.UID;

/**
 * Servlet implementation class ForgetPwServlet
 */
@WebServlet("/ForgetPwServlet")
public class ForgetPwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgetPwServlet() {
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
		String email = request.getParameter("email");
		UUID id = UID.genSessionId(); 
		HttpSession session = request.getSession();
		session.setAttribute("UUID", id);
		ArrayList<InternetAddress> to = new ArrayList<InternetAddress>();
		
		try {
			to.add(new InternetAddress(email));
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		MailSSL sender = new MailSSL(to,"Reset Password","Click the link below to reset your password.");
		sender.run();
		request.getRequestDispatcher("pages/login.jsp").forward(request, response);
	}

}
