package servlet.forum;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Post;
import database.CommentDB;
import database.ForumDB;
import database.NotificationDB;
import database.Point;

/**
 * Servlet implementation class pickBestAnswer
 */
@WebServlet("/pickBestAnswer")
public class pickBestAnswer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public pickBestAnswer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session.getAttribute("account") != null) {
			String postId = request.getParameter("postid");
			String commentId = request.getParameter("commentid");
			ForumDB fdb = new ForumDB();
			Post p = fdb.getPostById(postId);
			CommentDB cdb = new CommentDB();
			String ownerId = cdb.getCommentOwnerbyId(commentId);
			fdb.pickBestAnswer(postId,commentId);
			Point pt = new Point();
			pt.pointsCalc(ownerId, p.getPoints());
			NotificationDB no = new NotificationDB();
			no.sendNotification("Answer Choosen As Best Answer!", "Congrats! <br> Your reply was chosen by post owner as the best answer. <br> you have earn " + p.getPoints() + " points", "Forum", ownerId, "Check it out", "Post?postId="+postId);
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
