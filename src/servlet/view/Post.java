package servlet.view;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String postId = (request.getParameter("postId") != null )?request.getParameter("postId"):"";
		
		ArrayList<bean.Post> p = fdb.getPostById(postId);
		ArrayList<bean.Comment> c = cdb.getCommentByPostId(postId, 0, 10);
		System.out.println(p.size());
		if(p.size() > 0) {
			if(p.get(0).getValid() == 'Y') {
				request.setAttribute("postList", p);
				request.setAttribute("commentList", c);
			}else {
				request.setAttribute("message", "Post deleted by owner");
			}			
		}else{
			request.setAttribute("message", "Post not found");
		}

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
