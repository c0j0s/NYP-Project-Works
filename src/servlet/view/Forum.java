package servlet.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Account;
import bean.Post;
import database.ForumDB;

/**
 * Servlet implementation class toForumList
 */
@WebServlet("/Forum")
public class Forum extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Forum() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ForumDB fdb = new ForumDB();
		String category;
		String page;
		int start;
		
		if(request.getParameter("category") == null || request.getParameter("page") == null) {
			category = "general";
			page = "1";
		}else {
			category = (String) request.getParameter("category");
			page = (String) request.getParameter("page");
		}
		
		start = (Integer.parseInt(page) == 1) ? 0 : (Integer.parseInt(page) * 10) - 10;
		
		ArrayList<Post> postList = fdb.getPostSimpleList(start, category);
		ArrayList<Post> trendingPost = fdb.getTrendingPost();
		//ArrayList<Account> topAnswerer = fdb.getTopAnswerer();
		Collection<String> categoryList = fdb.getCategoryList().values();
		request.setAttribute("postList", postList);
		request.setAttribute("trendingPost", trendingPost);
		//request.setAttribute("topAnswerer", topAnswerer);
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("postCount", fdb.getPostCount(category));
		request.setAttribute("category", category);
		request.setAttribute("page", page);
		request.getRequestDispatcher("pages/forum.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
