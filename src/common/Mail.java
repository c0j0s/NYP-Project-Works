package common;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
/**
 * @author cjuns
 * 
 */
 
public class Mail {
 
	private static Properties properties;
	private static Session session;
	private static MimeMessage message;
	final private static String sender = "bt1602gp@gmail.com";
 
	public static void sendEmail(String to, String subject ,String emailBody){
 
		properties = System.getProperties();
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
 
		session = Session.getDefaultInstance(properties, null);
		message = new MimeMessage(session);
		
		try {
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setFrom(new InternetAddress(sender,"FamForLife"));
			message.setSubject("FamForLife: " + subject);
			message.setContent(emailBody, "text/html");
			
			
		} catch (AddressException e) {
			System.out.println(e.getMessage());
		} catch (MessagingException e) {
			System.out.println(e.getMessage());
		}  catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		}
 
		try {
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.gmail.com", sender, "admin@gmail");
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			System.out.println("mail sent to:" + to);
			
			
		} catch (MessagingException e) {
			System.out.println(e.getMessage());
		}
	}
}