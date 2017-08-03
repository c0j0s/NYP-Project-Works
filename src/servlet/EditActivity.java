package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bean.Activity;
import database.ActivityDB;

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
			ActivityDB adb = new ActivityDB();
			Activity act = adb.getActivityById(request.getParameter("activityId")).get(0);


			act.setActivityTitle(request.getParameter("actName"));
			act.setActivityDescription(request.getParameter("actDesc"));
			act.setParticipantNo(Integer.parseInt(request.getParameter("actPart")));
			act.setActivityCategory(request.getParameter("actCategory"));

			String img = request.getParameter("imgurl");
			if(!img.equals("")) {
				act.setImgUrl(img);
			}
			adb.editActivity(act);
			response.sendRedirect("ActFull?activityId="+act.getActivityId());
		} catch (Exception e) {
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
