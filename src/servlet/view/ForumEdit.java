package servlet.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Account;
import bean.Post;
import database.ForumDB;

/**
 * Servlet implementation class ForumEdit
 */
@WebServlet("/ForumEdit")
public class ForumEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForumEdit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ForumDB fdb = new ForumDB();
		String mode = request.getParameter("mode");
		String postId = request.getParameter("postId");
		String path = "";
		try {
			if(mode.equals("create")) {
				Map<String, String> categoryList = fdb.getCategoryList();
				request.setAttribute("categoryList", categoryList);
				path = "pages/forum-edit.jsp";
				request.getRequestDispatcher(path).forward(request, response);
			}else {
				HttpSession ss = request.getSession(true);
				Account ac = (Account) ss.getAttribute("account");
				Post oldP = fdb.getPostById(request.getParameter("postId"));
				if(!oldP.getAccountId().equals(ac.getAccountId())) {
					throw new Exception();
				}else {
					Post p = fdb.getPostById(request.getParameter("postId"));
					request.setAttribute("postList", p);
					path = "pages/forum-edit.jsp";
					request.getRequestDispatcher(path).forward(request, response);
				}
			}
			
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
