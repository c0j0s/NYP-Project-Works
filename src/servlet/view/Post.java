package servlet.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String postId = (request.getParameter("postId") != null )?request.getParameter("postId"):"";
		
		ArrayList<bean.Post> p = fdb.getPostById(postId);
		ArrayList<bean.Comment> c = cdb.getCommentByPostId(postId, 0, 10);
		Iterator<bean.Comment> comIter = c.iterator();
		Comment bestAnswer = null;
		
		while(comIter.hasNext()) {
			Comment com = (bean.Comment) comIter.next();
			
			if(com.getCommentId().equals(p.get(0).getBestAnswer())) {
				bestAnswer = com;
				comIter.remove();
				System.out.println("servlet 1 match " + com.getAccountId());
			}else {
				System.out.println("servlet 1 " + com.getAccountId());
			}
		}

		ArrayList<bean.Comment> newC = new ArrayList<bean.Comment>();
		if(bestAnswer != null) {
			newC.add(bestAnswer);
		}
		newC.addAll(c);
		
		System.out.println(p.size());
		if(p.size() > 0) {
			if(p.get(0).getValid() == 'Y') {
				System.out.println("log comment size:"+newC.size());
				request.setAttribute("postList", p);
				request.setAttribute("commentList", newC);
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
