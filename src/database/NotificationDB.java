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
	
	/**
	 * get users notifications
	 * @param stmt
	 * @param accountId
	 * @return
	 */
	public ArrayList<Notification> getAccountNotifications(String stmt,String accountId) {
		//
		ArrayList<Notification> list = new ArrayList<Notification>();
		try {
			if(stmt == null) {
				stmt = "Select * from ffl.notification where accountId = ?  order by createdOn DESC";
			}
			PreparedStatement ps = con.prepareStatement(stmt);
			ps.setString(1, accountId);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Notification no = new Notification();
				no.setAccountId(accountId);
				no.setId(rs.getString("notificationId"));
				no.setCreatedOn(rs.getString("createdOn"));
				no.setTitle(rs.getString("title"));
				no.setMessage(rs.getString("message"));
				no.setServiceType(rs.getString("serviceType"));
				no.setActionText(rs.getString("actionText"));
				no.setActionUrl(rs.getString("actionUrl"));
				list.add(no);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * get users unread notifications
	 * @param accountId
	 * @return
	 */
	public ArrayList<Notification> getAccountUnNotifications(String accountId) {
		String stmt = "Select * from ffl.notification where accountId = ? and `read` = 'N' order by createdOn DESC";
		return getAccountNotifications(stmt,accountId);
	}

	/**
	 * get user unread notification count
	 * @param accountId
	 * @return
	 */
	public int getNotificationCount(String accountId) {
		
		int count = 0;
		try {
			String stmt = "Select COUNT(*) from ffl.notification where accountId = ? and `read` = 'N' ";
			PreparedStatement ps = con.prepareStatement(stmt);
			ps.setString(1, accountId);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
			ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	/**
	 * sent notification to specific users
	 * @param title
	 * @param message
	 * @param serviceType
	 * @param accountId
	 * @param actionText
	 * @param actionUrl
	 */
	public void sendNotification(String title,String message,String serviceType,String accountId,String actionText,String actionUrl) {
		
		String stmt = "Insert into ffl.notification (title,message,serviceType,accountId,actionText,actionUrl) values (?,?,?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(stmt);
			ps.setString(1, title);
			ps.setString(2, message);
			ps.setString(3, serviceType);
			ps.setString(4, accountId);
			ps.setString(5, actionText);
			ps.setString(6, actionUrl);
			
			int status = ps.executeUpdate();
			if (status != 0) {
				System.out.println("create notification success");
				ps.close();
				
				return;
			}else {
				System.out.println("Log notifiaction ps: ("+accountId+")" + ps);
				ps.close();
				
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * sent notification to multiple users
	 * @param accountIds
	 * @param no
	 */
	public void sentNotificationToAccounts(ArrayList<String> accountIds,Notification no) {
		for (Iterator<String> iterator = accountIds.iterator(); iterator.hasNext();) {
			String accountId = (String) iterator.next();
			sendNotification(no.getTitle(),no.getMessage(),no.getServiceType(),accountId,no.getActionText(),no.getActionUrl());
		}
	}

	/**
	 * set nitification status to read
	 * @param accountId
	 * @param id
	 */
	public void setRead(String accountId, String id) {
		
		String stmt = "Update ffl.notification set `read` = 'Y' where notificationId = ? and accountId = ?";
		try {
			PreparedStatement ps = con.prepareStatement(stmt);
			ps.setString(1, id);
			ps.setString(2, accountId);
			
			int status = ps.executeUpdate();
			if (status != 0) {
				System.out.println("read notification success");
				ps.close();
				
				return;
			}else {
				System.out.println("Log notifiaction ps: " + ps);
				ps.close();
				
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
