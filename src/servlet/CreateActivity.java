package servlet;

import java.io.IOException;
import java.util.Arrays;

import bean.Account;
import bean.Activity;
import database.ActivityDB;
import database.DBAO;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		try {	HttpSession s = request.getSession(true);
			ActivityDB actdb = new ActivityDB();
			Activity act = new Activity();
			Account ac = (Account)s.getAttribute("account");
		
			act.setActivityTitle(request.getParameter("actName"));
			act.setActivityPostDate(DBAO.getDateTime());
			act.setActivityEndDate(request.getParameter("actEnd"));
			act.setActivityStartDate(request.getParameter("actStart"));
			act.setActivityDescription(request.getParameter("actDesc"));
			
			System.out.println(request.getParameter("actPart"));
			act.setParticipantNo(Integer.parseInt(request.getParameter("actPart")));
			act.setActivityRegistrationEnd(request.getParameter("RegEnd"));
			act.setActivityFee(Double.valueOf(request.getParameter("actFeeDollars")+"."+request.getParameter("actFeeCents")));
			act.setActivityLocation(request.getParameter("actLocation"));
			act.setActivityCategory(request.getParameter("actCategory"));
			
			System.out.println(request.getParameter("imgurl"));
			act.setImgUrl(request.getParameter("imgurl"));
			StringBuilder builder = new StringBuilder();
			String day[] =request.getParameterValues("actDay");
			for (String value : day) {
			    builder.append(value);
			}
			String days = builder.toString();
		
			
			act.setActivityDay(days);
			act.setAccountId(ac.getAccountId());
		
			act.setActivityTime(request.getParameter("actTimeHour")+":"+request.getParameter("actTimeMin")+" "+request.getParameter("actTimeM"));
			act.setActivityRegistrationEnd(request.getParameter("actRegEnd"));
			act.setActivityId(actdb.createActivity(act));

			if(!act.getActivityId().equals("fail") || act.getActivityId() == null){
				request.getRequestDispatcher("/pages/activityfull.jsp?actId="+act.getActivityId()).forward(request, response);
			
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
