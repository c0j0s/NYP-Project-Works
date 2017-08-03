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
import bean.FamilyGrp;
import database.ActivityDB;
import database.FamGrpDB;
import database.Point;

/**
 * Servlet implementation class ActRegLink
 */
@WebServlet("/ActReg")
public class ActReg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActReg() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ActivityDB adb = new ActivityDB();
		Point p = new Point();
		ArrayList<Activity> actRank = p.getRank();
		FamGrpDB fgdb = new FamGrpDB();
		Account ac = (Account) request.getSession().getAttribute("account");
		String acctId = ac.getAccountId();
		request.setAttribute("acctId", acctId);
		System.out.println(acctId);
		ArrayList<FamilyGrp> famGrp = fgdb.getAllFamMem(acctId);
		request.setAttribute("allFam" , famGrp);
		System.out.println( ac+"EVERYONE HELP ME");
		request.setAttribute("actRank", actRank);
		String RegistrationId = common.UID.genRegistrationId();
		request.setAttribute("registerId",RegistrationId);
		ArrayList<Activity> activityRegistration = adb.getActivityById(request.getParameter("activityId"));
		request.setAttribute("activityRegistration", activityRegistration);
		request.getRequestDispatcher("pages/RegAct.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
