package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Result;
import database.DBAO;
import database.SearchDB;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyWord = request.getParameter("globalSearch");
		if (!keyWord.equals("")) {
			String searchIn = request.getParameter("searchIn");
			String servletPath = (searchIn.equals("post")) ? "Post" : "ActFull";
			SearchDB db = new SearchDB();
			ArrayList<Result> resultList;
			if(searchIn.equals("all")) {
				resultList = db.searchAll(keyWord);
			}else {
				resultList = db.searchSpecific(searchIn,keyWord,servletPath);
			}
		
			request.setAttribute("searchIn", searchIn);
			request.setAttribute("keyWord", keyWord);
			request.setAttribute("resultList", resultList);
		}
		request.getRequestDispatcher("pages/search.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
