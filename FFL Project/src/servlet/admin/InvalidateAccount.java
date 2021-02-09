package servlet.admin;

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
 * Servlet implementation class InvalidateAccount
 */
@WebServlet("/InvalidateAccount")
public class InvalidateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InvalidateAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		AccountDB acdb = new AccountDB();System.out.println(request.getParameter("accId"));
		if(request.getParameter("accId")!=null){
			String accId =(request.getParameter("accId"));
			acdb.invalidateAccount(accId);
			acdb.updateReportStatus("deleted");
			response.getWriter().append("Account invalidated");
		}else 
		{
			HttpSession session = request.getSession(true);
			Account ac = (Account) session.getAttribute("account");
			acdb.invalidateAccount(ac.getAccountId());
			request.getRequestDispatcher("LogoutServlet").forward(request, response);
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
