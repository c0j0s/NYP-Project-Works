package servlet.metavalue;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Account;
import database.MetaValueDB;

/**
 * Servlet implementation class CheckUserFollowedPost
 */
@WebServlet("/CheckUserFollowedPost")
public class CheckUserFollowedPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckUserFollowedPost() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ss = request.getSession(false);
		if(ss.getAttribute("account") != null) {
			Account ac = (Account) ss.getAttribute("account");
			MetaValueDB mdb = new MetaValueDB();
			String itemId = request.getParameter("id");
			boolean result = mdb.checkFollowed(itemId, ac.getAccountId());
			System.out.println(Boolean.toString(result));
			response.getWriter().append(Boolean.toString(result));
		}else {
			response.getWriter().append("Session Expired");
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
