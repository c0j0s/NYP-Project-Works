package servlet;

import java.io.IOException;
import bean.Activity;
import bean.Post;
import database.ActivityDB;
import database.DBAO;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateActivity
 */
@WebServlet("/CreateActivity")
public class CreateActivity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateActivity() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ActivityDB actdb = new ActivityDB();
			Activity act = new Activity();
			
			act.setActivityTitle(request.getParameter("actName"));
			act.setActivityPostDate(DBAO.getDateTime());
			act.setActivityEndDate(request.getParameter("actEnd"));
			act.setActivityStartDate(request.getParameter("actStart"));
			act.setActivityDescription(request.getParameter("actDesc"));
			act.setParticipantNo(Integer.parseInt(request.getParameter("actPar")));
			act.setActivityRegistrationEnd(request.getParameter("RegEnd"));
			act.setActivityFee(Double.valueOf(request.getParameter("actFee")));
			act.setActivityLocation(request.getParameter("actLocation"));
			act.setActivityCategory(request.getParameter("actCategory"));
			act.setImgUrl(request.getParameter("actImg"));
			act.setActivityDay(request.getParameter("actDay"));
			act.setActivityTime(request.getParameter("actTime"));
			act.setActivityRegistrationEnd(request.getParameter("actRegEnd"));
			act.setActivityId(actdb.createActivity(act));

			if(!act.getActivityId().equals("fail") || act.getActivityId() == null){
				String path = "pages/activityList.jsp";
				response.sendRedirect(path);
			}else{
				response.sendRedirect("pages/activityList.jsp");
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
