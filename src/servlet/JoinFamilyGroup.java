package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.FamilyGrp;
import database.FamGrpDB;

/**
 * Servlet implementation class JoinFamilyGroup
 */
@WebServlet("/JoinFamilyGroup")
public class JoinFamilyGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinFamilyGroup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			FamGrpDB fgdb = new FamGrpDB();
			String grpId = request.getParameter("grpId");
			String grpPassword = request.getParameter("grpPassword");
			ArrayList<FamilyGrp> fGrp = fgdb.getFamGrpAccurate(grpId,grpPassword);
			System.out.println("log a:"+ fGrp.size());
			request.setAttribute("fGroup", fGrp);
			request.getRequestDispatcher("DisplayFamGroup").forward(request, response);
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
