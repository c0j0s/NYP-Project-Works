package servlet.view;

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
 * Servlet implementation class CreateFamGroup
 */
@WebServlet("/CreateFamGroup")
public class CreateFamGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateFamGroup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FamGrpDB fgdb= new FamGrpDB();
		String famGroup = request.getParameter("userIdFg");
		System.out.println("HELP ME LUH"+famGroup);
		ArrayList<FamilyGrp> fGrp = fgdb.getFamGrpByUserId(famGroup);
		request.setAttribute("fGroup", fGrp);
		
		request.getRequestDispatcher("pages/createFamilyGrp.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
