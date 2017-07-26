package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Account;
import bean.Activity;
import database.ActivityDB;
import database.DBAO;

/**
 * Servlet implementation class EditActivity
 */
@WebServlet("/EditActivity")
public class EditActivity extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditActivity() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			HttpSession s = request.getSession(true);
		ActivityDB actdb = new ActivityDB();
		Activity act = new Activity();

		act.setActivityTitle(request.getParameter("actName"));
		act.setActivityDescription(request.getParameter("actDesc"));
		act.setParticipantNo(Integer.parseInt(request.getParameter("actPart")));
		act.setActivityCategory(request.getParameter("actCategory"));
		 String img = request.getParameter("imgurl");
		if(!(img ==null)) {act.setImgUrl(img);}
		act.setActivityId(actdb.createActivity(act));
		if(!act.getActivityId().equals("fail") || act.getActivityId() == null){
			request.getRequestDispatcher("/pages/activityfull.jsp?activityId="+act.getActivityId()).forward(request, response);

		}else{
			request.getRequestDispatcher("/pages/activity-create.jsp").forward(request, response);
			System.out.println("Log createActivity.java: fail to create activity");
		}
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
