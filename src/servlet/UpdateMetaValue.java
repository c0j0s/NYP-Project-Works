package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Account;
import database.MetaValueDB;

/**
 * Servlet implementation class UpdateMetaValue
 */
@WebServlet("/UpdateMetaValue")
public class UpdateMetaValue extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMetaValue() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession s = request.getSession(true);
			MetaValueDB m = new MetaValueDB();
			PrintWriter out = response.getWriter();
			String newCount;
			System.out.println("meta sevlet doget");
			Account ac = (Account)s.getAttribute("account");
			String id = request.getParameter("id");
			String action = request.getParameter("action");
			String operators = request.getParameter("operator");
			System.out.println(id + action + operators);
			if(operators.equals("add")) {
				newCount = m.addMeta(id, ac.getAccountId(), action);
				System.out.println(newCount);
				out.print(newCount);
			}else if (operators.equals("subtract")) {
				newCount = m.removeMeta(id, ac.getAccountId(), action);
				System.out.println(newCount);
				out.print(newCount);
			}
			out.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
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
