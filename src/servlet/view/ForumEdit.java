package servlet.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DBAO;
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
		System.out.println("Log ForumEdit:" + mode);
		if(mode.equals("edit")) {
			ArrayList<bean.Post> p = new ArrayList<bean.Post>(); 
			p = fdb.getPostById(request.getParameter("postId"));
			Map<String, String> categoryList = fdb.getCategoryList();
			request.setAttribute("categoryList", categoryList);
			request.setAttribute("postList", p);
			path = "pages/forum-edit.jsp";
		}else if (mode.equals("delete")) {
			fdb.invalidPost(postId);
			path = "/Forum";
		}
		
		request.getRequestDispatcher(path).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}