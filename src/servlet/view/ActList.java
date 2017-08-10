package servlet.view;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Account;
import bean.Activity;
import database.ActivityDB;
import database.Point;

/**
 * Servlet implementation class ActList
 */
@WebServlet("/ActList")
public class ActList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ActList() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ActivityDB adb = new ActivityDB();
		Point p = new Point();
		
		String page = (request.getParameter("page") != null )?request.getParameter("page"):"1";
		int start = (Integer.parseInt(page) == 1) ? 0 : (Integer.parseInt(page) * 5) - 5;
		ArrayList<Activity> actRank = p.getRank();
		int totalNo = adb.getTotalActivityCount();
		request.setAttribute("page", page);
		request.setAttribute("actCount", totalNo);
		request.setAttribute("actRank", actRank);
		ArrayList<Activity> activityList = adb.getActivity(null,start);
		request.setAttribute("activityList", activityList);
		request.getRequestDispatcher("pages/activityList.jsp").forward(request, response);
	
	 }






	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
