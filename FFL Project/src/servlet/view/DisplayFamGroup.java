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
import database.FamGrpDB;

/**
 * Servlet implementation class FamilyGroup
 */
@WebServlet("/DisplayFamGroup")
public class DisplayFamGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayFamGroup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {FamGrpDB fgdb = new FamGrpDB();
		
			
				ArrayList<FamilyGrp> aFgrp = fgdb.findMembers(request.getParameter("famGrpId"));
				Account ac = (Account) request.getSession().getAttribute("account");
				System.out.println( ac.getAccountId());
				request.setAttribute("accountId", ac.getAccountId());
				request.setAttribute("fGroup", aFgrp);
				ArrayList<FamilyGrp> member = fgdb.findGrpMembers(request.getParameter("famGrpId"));
				request.setAttribute("members", member);
			System.out.println("log s:"+ aFgrp.size());
			
			request.getRequestDispatcher("pages/familyGroup.jsp").forward(request, response);
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
