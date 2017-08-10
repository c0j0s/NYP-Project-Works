package servlet.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import bean.Account;
import database.AdminDB;

/**
 * Servlet implementation class ReportItem
 */
@WebServlet("/ReportItem")
public class ReportItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportItem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ss = request.getSession(true);
		if (ss.getAttribute("account") != null) {
			Account ac =(Account) ss.getAttribute("account");
			String itemId = request.getParameter("itemid");
			String type = request.getParameter("type");
			String reasons = request.getParameter("reasons");
			AdminDB adb = new AdminDB();
			try {
				response.getWriter().append(adb.reportItem(itemId, ac.getAccountId(),type,reasons));
			} catch (MySQLIntegrityConstraintViolationException e) {
				response.getWriter().append("You have reported " + type + " before.");
			}
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
