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

import org.apache.commons.lang3.StringUtils;

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
		String type = request.getParameter("type");
		String mode = request.getParameter("mode");
		String postId = request.getParameter("postId");
		String path = "";
		try {
			Map<String, String> categoryList = fdb.getCategoryList();
			request.setAttribute("categoryList", categoryList);
			
			if(mode.equals("create")) {
				path = "pages/forum-edit.jsp";
			}else {
				HttpSession ss = request.getSession(true);
				Account ac = (Account) ss.getAttribute("account");
				Post oldP = fdb.getPostById(request.getParameter("postId")).get(0);
				if(!oldP.getAccountId().equals(ac.getAccountId())) {
					throw new Exception();
				}else {
					if(mode.equals("edit")) {
						ArrayList<bean.Post> p = new ArrayList<bean.Post>(); 
						p = fdb.getPostById(request.getParameter("postId"));
						request.setAttribute("postList", p);
						path = "pages/forum-edit.jsp";
					}else if (mode.equals("delete")) {
						fdb.invalidPost(postId);
						path = "/Forum";
					}
				}
			}
			request.getRequestDispatcher(path).forward(request, response);
		} catch (Exception e) {
			request.getRequestDispatcher("Post?postId=" + postId).forward(request, response);
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
