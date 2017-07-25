package servlet.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Account;
import bean.Comment;
import database.CommentDB;
import database.ForumDB;

/**
 * Servlet implementation class toPost
 */
@WebServlet("/Post")
public class Post extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Post() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ForumDB fdb = new ForumDB();
		CommentDB cdb = new CommentDB();
		HttpSession ss = request.getSession(true);
		Account ac = (Account) ss.getAttribute("account");
		boolean followed = false;
		String postId = (request.getParameter("postId") != null )?request.getParameter("postId"):"";
		String page = (request.getParameter("page") != null )?request.getParameter("page"):"1";
		int start = (Integer.parseInt(page) == 1) ? 0 : (Integer.parseInt(page) * 10) - 9;
		
		bean.Post p = fdb.getPostById(postId).get(0);
		ArrayList<bean.Comment> c = cdb.getCommentByPostId(postId, start, 5);
		
		if(p != null) {
			if(p.getValid() == 'Y') {
				request.setAttribute("post", p);
				request.setAttribute("commentList", c);
			}else {
				request.setAttribute("message", "Post deleted by owner");
			}			
		}else{
			request.setAttribute("message", "Post not found");
		}
		if(ac != null) {
			if(p.getFollowerAccounts().contains(ac.getAccountId())){
				followed = true;
			}
		}
		
		request.setAttribute("followed", followed);
		request.setAttribute("page", page);
		request.getRequestDispatcher("pages/post.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
