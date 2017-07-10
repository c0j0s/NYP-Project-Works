package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Account;
import bean.Post;
import database.DBAO;
import database.ForumDB;

/**
 * Servlet implementation class EditPost
 */
@WebServlet("/EditPost")
public class EditPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditPost() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s = request.getSession(true);
		Account ac = (Account)s.getAttribute("account");
		ForumDB fdb = new ForumDB();
		
		Post oldP = fdb.getPostById(request.getParameter("postId")).get(0);
		Map<String, String> input = new HashMap<String, String>();
		
		if(!request.getParameter("postTitle").equals(oldP.getPostTitle())) {
			input.put("postTitle", request.getParameter("postTitle"));
		}
		if(!request.getParameter("postContent").equals(oldP.getPostContent())) {
			String newContent = request.getParameter("postContent");
			String oldContent = oldP.getPostContent();
			String sepeartor = "<hr><p>Last updated on:"+ fdb.getDateTime() +"</p><hr>";
			newContent = newContent + sepeartor + "<div class=\"post-old-content\"><p>" +oldContent + "</p></div>";
			
			input.put("postContent", newContent);
		}
		if(!request.getParameter("postCategory").equals(oldP.getPostCategory())) {
			input.put("postCategory", request.getParameter("postCategory"));
		}
		if(!request.getParameter("postTags").equals(oldP.getTagList())) {
			input.put("postTags", request.getParameter("postTags"));
		}
		if(request.getParameter("hideId") != null) {
			if(!request.getParameter("hideId").equals(oldP.getHideId())) {
				input.put("hideId", request.getParameter("hideId"));
			}
		}
		
		
		oldP.setPostId(fdb.updatePost(input,oldP.getPostId()));
		
		String path = "";
		if(!oldP.getPostId().equals("fail") || oldP.getPostId() == null){
			path = "Post?postId=" + oldP.getPostId();
		}else{
			path = "ForumEidt?mode=create";
			System.out.println("Log createPost.java: fail to create post");
		}
		response.sendRedirect(path);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
