package servlet.forum;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.CommentDB;

/**
 * Servlet implementation class InvalidComment
 */
@WebServlet("/InvalidComment")
public class InvalidComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InvalidComment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String id = request.getParameter("commentId");
			System.out.println("id:" + id);
			CommentDB cdb = new CommentDB();
			if(cdb.invalidComment(id)) {
				response.getWriter().append("Comment removed.");
			}else {
				response.getWriter().append("Fail to remove comment.");
			}
		} catch (Exception e) {
			response.getWriter().append("Server error: fail to remove comment");
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
