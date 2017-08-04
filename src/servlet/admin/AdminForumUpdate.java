package servlet.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.AdminDB;
import database.CommentDB;
import database.ForumDB;
import database.NotificationDB;

/**
 * Servlet implementation class AdminForumUpdate
 */
@WebServlet("/AdminForumUpdate")
public class AdminForumUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminForumUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String itemId = request.getParameter("itemid");
		String type = request.getParameter("type");
		System.out.println(type);
		AdminDB adb = new AdminDB();
		adb.updateReportStatus(action,itemId);
		if(action.equals("deleted")) {
			NotificationDB ndb = new NotificationDB();
			if(type.equals("post")) {
				ForumDB fdb = new ForumDB();
				fdb.invalidPost(itemId);
				String accountId = fdb.getItemAccountIdByItemId(itemId);
				ndb.sendNotification("Post Deleted by Admin", "Your post ("+ itemId +") has been deleted due to violation of forum rules", "FFL Admin", accountId);
			}else if (type.equals("comment")) {
				CommentDB cdb = new CommentDB();
				cdb.invalidComment(itemId);
				String accountId = cdb.getItemAccountIdByItemId(itemId);
				ndb.sendNotification("Comment Deleted by Admin", "Your post ("+ itemId +") has been deleted due to violation of forum rules", "FFL Admin", accountId);
			}
		}else if (action.equals("cancel")){
			if(type.equals("post")) {
				ForumDB fdb = new ForumDB();
				fdb.validPost(itemId);
			}else if (type.equals("comment")) {
				CommentDB cdb = new CommentDB();
				cdb.validComment(itemId);
			}
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
