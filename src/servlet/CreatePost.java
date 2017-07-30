package servlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import bean.Account;
import bean.Post;
import database.DBAO;
import database.ForumDB;
import database.Point;

/**
 * Servlet implementation class createPost
 */
@WebServlet("/CreatePost")
public class CreatePost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreatePost() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession s = request.getSession(true);
			Account ac = (Account)s.getAttribute("account");
			Point point = new Point();
			ForumDB f = new ForumDB();
			Post p = new Post();
			p.setPostTitle(request.getParameter("postTitle"));
			p.setDate(DBAO.getDateTime());
			p.setPostContent(request.getParameter("postContent"));
			p.setPostCategory(request.getParameter("postCategory"));
			p.setTagList(request.getParameter("postTags"));
			p.setPoints(Integer.parseInt(request.getParameter("postPoints")));
			p.setAccountId(ac.getAccountId());
			if(request.getParameter("hideId") != null){
				p.setHideId(request.getParameter("hideId").charAt(0));
			}else{
				p.setHideId('N');
			}
			p.setPostId(f.createPost(p));
			point.pointsCalc(ac.getAccountId(), -p.getPoints());
			String path = "";
			if(!p.getPostId().equals("fail") || p.getPostId() == null){
				path = "Post?postId=" + p.getPostId();
			}else{
				path = "ForumEdit?mode=create";
				System.out.println("Log createPost.java: fail to create post");
			}
			response.sendRedirect(path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
