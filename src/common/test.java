package common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import bean.Notification;
import database.DBAO;
import database.ForumDB;
import database.NotificationDB;
import database.Point;;

public class test {

	public static void main(String[] args) {
//		ForumDB fdb = new ForumDB();
//		Map<String, String> input = new HashMap<String, String>();
//		input.put("c", "v");
//		input.put("b", "b");
//		fdb.updatePost(input, "POS0000000");
		
//		NotificationDB db = new NotificationDB();
//		Notification no = new Notification();
//		no.setTitle("test");
//		no.setMessage("body");
//		no.setServiceType("Forum");
//		no.setAccountId("ACC0000000");
//		db.createNotification(no);
		
//		db.setRead("1");
		
//		ArrayList<Notification> list = db.getAccountNotifications("ACC0000000");
//		for (Iterator<Notification> iterator = list.iterator(); iterator.hasNext();) {
//			Notification notification = (Notification) iterator.next();
//			System.out.println(notification.getMessage());
//		}
//		System.out.println(db.getNotificationCount("ACC0000000"));
		//ArrayList<InternetAddress> to = new ArrayList<InternetAddress>();
		
		//try {
		//	to.add(new InternetAddress("joshlimwk@gmail.com"));
		//} catch (AddressException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}

		//MailSSL sender = new MailSSL(to,"Reset Password","Click the link below to reset your password.");
		//sender.run();
	//	Mail mail = new Mail("");
		//mail.sendSimpleMail();
		Point p = new Point();
		p.pointsCalc("ACC0000000",500);
		p.calcRank();
		
	}

}
