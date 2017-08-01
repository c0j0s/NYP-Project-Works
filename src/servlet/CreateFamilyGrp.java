package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.FamilyGrp;
import common.UID;
import database.AccountDB;
import database.DBAO;
import database.FamGrpDB;

/**
 * Servlet implementation class CreateFamilyGrp
 */
@WebServlet("/CreateFamilyGrp")
public class CreateFamilyGrp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateFamilyGrp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			HttpSession s = request.getSession(true);
			FamGrpDB fgdb = new FamGrpDB();
			FamilyGrp fg = new FamilyGrp();
		
		fg.setFamilyGroupId(UID.genFamilyGroupId());
		fg.setGroupName(request.getParameter("grpName"));
		fg.setImgUrl(request.getParameter("imgurl"));
		fg.setGrpOwner(request.getParameter("owner"));
		fg.setGroupCreationDate(DBAO.getDateTime());
		fg.setPassword(request.getParameter("password"));
		fgdb.createFamGrp(fg);
			
			
			request.getRequestDispatcher("CreateFamGroup").forward(request, response);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
	}}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}
}
