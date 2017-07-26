package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import bean.Notification;

public class NotificationDB extends DBAO{

	public NotificationDB() {
		super();
	}
	
	public ArrayList<Notification> getAccountNotifications(String stmt,String accountId) {
		ArrayList<Notification> list = new ArrayList<Notification>();
		try {
			if(stmt == null) {
				stmt = "Select * from ffl.notification where accountId = ? order by createdOn DESC";
			}
			PreparedStatement ps = con.prepareStatement(stmt);
			ps.setString(1, accountId);
			
			System.out.println(ps);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Notification no = new Notification();
				no.setAccountId(accountId);
				no.setId(rs.getString("notificationId"));
				no.setCreatedOn(rs.getString("createdOn"));
				no.setTitle(rs.getString("title"));
				no.setMessage(rs.getString("message"));
				no.setServiceType(rs.getString("serviceType"));
				list.add(no);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<Notification> getAccountUnNotifications(String accountId) {
		String stmt = "Select * from ffl.notification where accountId = ? and `read` = 'N' order by createdOn DESC";
		return getAccountNotifications(stmt,accountId);
	}

	public int getNotificationCount(String accountId) {
		int count = 0;
		try {
			String stmt = "Select COUNT(*) from ffl.notification where accountId = ? and `read` = 'N' ";
			PreparedStatement ps = con.prepareStatement(stmt);
			ps.setString(1, accountId);
			
			System.out.println(ps);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public void sendNotification(String title,String message,String serviceType,String accountId) {
		String stmt = "Insert into ffl.notification (title,message,serviceType,accountId) values (?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(stmt);
			ps.setString(1, title);
			ps.setString(2, message);
			ps.setString(3, serviceType);
			ps.setString(4, accountId);
			
			int status = ps.executeUpdate();
			if (status != 0) {
				System.out.println("create notification success");
				return;
			}else {
				System.out.println("Log notifiaction ps: ("+accountId+")" + ps);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sentNotificationToAccounts(ArrayList<String> accountIds,Notification no) {
		for (Iterator<String> iterator = accountIds.iterator(); iterator.hasNext();) {
			String accountId = (String) iterator.next();
			sendNotification(no.getTitle(),no.getMessage(),no.getServiceType(),accountId);
		}
	}

	public void setRead(String accountId, String id) {
		String stmt = "Update ffl.notification set `read` = 'Y' where notificationId = ? and accountId = ?";
		try {
			PreparedStatement ps = con.prepareStatement(stmt);
			ps.setString(1, id);
			ps.setString(2, accountId);
			System.out.println("Log notifiaction ps: " + ps);
			
			int status = ps.executeUpdate();
			if (status != 0) {
				System.out.println("read notification success");
				return;
			}else {
				System.out.println("Log notifiaction ps: " + ps);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
