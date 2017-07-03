package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Account;
import database.MetaValueDB;

/**
 * Servlet implementation class UpdateMetaValue
 */
@WebServlet("/UpdateMetaValue")
public class UpdateMetaValue extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMetaValue() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession s = request.getSession(true);
			Account ac = (Account)s.getAttribute("account");
			
			String id = request.getParameter("id");
			String colName = request.getParameter("colname");
			String action = request.getParameter("action");
			MetaValueDB m = new MetaValueDB();
			if(request.getParameter("mode").equals("add")){
				m.addMeta(colName, id, ac.getAccountId(), action);
			}else{
				m.removeMeta(colName, id, ac.getAccountId(), action);
			}
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
