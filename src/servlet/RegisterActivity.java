package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Account;
import bean.ActReg;
import database.ActRegDB;
import database.ActivityDB;
import database.DBAO;
import database.Point;

/**
 * Servlet implementation class RegAct
 */
@WebServlet("/RegisterActivity")
public class RegisterActivity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterActivity() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {	HttpSession s = request.getSession(true);
		ActRegDB ardb = new ActRegDB();
		ActivityDB adb = new ActivityDB();
		ActReg ar = new ActReg();
		Account ac = (Account)s.getAttribute("account");
		Point p = new Point();
		String actId = request.getParameter("activityId");
		int pay = Integer.parseInt(request.getParameter("countpay"));
		adb.editParNo(actId, pay);
		p.pointsCalc(request.getParameter(ac.getAccountId()),10);
		ar.setRegistrationId(request.getParameter("registerId"));
		ar.setRegistrationAmtPaid(Double.valueOf(request.getParameter("total")));
		ar.setParticipantNo(Integer.parseInt(request.getParameter("countpay")));
		ar.setUserAccountId(ac.getAccountId());
		ar.setActivityRegistrationDate(DBAO.getDateTime());
		ar.setCashOrBank(request.getParameter("type"));
		ar.setActivityactivityId(request.getParameter("activityId"));
		ar.setParticipantId(new ArrayList<String>(Arrays.asList(request.getParameterValues("multiselect[]"))));
		for(String id : ar.getParticipantId()){
		p.pointsCalc(id , 30);}
		System.out.println(ar.getParticipantId().size());
		ardb.RegisterActivity(ar);
		ardb.setRegistrationList(ac.getAccountId(), ar.getRegistrationId());
		for(String reg: ar.getParticipantId() ) {
			ardb.setRegistrationList(reg, ar.getRegistrationId());
		}
		System.out.println("pls help"+ar);
		response.sendRedirect("ActList");
		}catch(Exception ex) {
			ex.printStackTrace();
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
