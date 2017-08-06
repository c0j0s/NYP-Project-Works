package servlet.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
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
		HttpSession ss = request.getSession(true);
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
		request.setAttribute("postList", postList);
		Collection<String> categoryList = fdb.getCategoryList().values();
		request.setAttribute("categoryList", categoryList);
		
		if(ss.getAttribute("trendingPost") != null) {
			request.setAttribute("trendingPost", ss.getAttribute("trendingPost"));
		}else {
			ArrayList<Post> trendingPost = fdb.getTrendingPost();
			request.setAttribute("trendingPost", trendingPost);
			ss.setAttribute("trendingPost", trendingPost);
		}
		
		
		if(ss.getAttribute("MaxCount") != null) {
			request.setAttribute("postCount", ss.getAttribute("MaxCount"));
		}else {
			int maxCount = fdb.getPostCount(category);
			ss.setAttribute("MaxCount", maxCount);
			request.setAttribute("postCount", maxCount);
		}
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
