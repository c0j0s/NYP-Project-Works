package common;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
 
	private static Properties properties;
	private static Session session;
	private static MimeMessage message;
	final private static String sender = "bt1602gp@gmail.com";
	final private static String password = "admin@gmail";
	private String path;
	
	public Mail(){
		properties = System.getProperties();
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		//this.path = path;
	}
	public void sendNotificationMail(String to, String subject, String title, String htmlbody){
		Map<String, String> input = new HashMap<String, String>();
		input.put("FFL:path", "http://localhost:8080/FFL");
		input.put("FFL:title", title);
		input.put("FFL:message", htmlbody);
		input.put("FFL:need-button", "none");
		sendEmail(to,subject,input);
	}
	public void sendActionMail(String to, String subject, String title, String htmlbody,String buttonText,String buttonLink){
		Map<String, String> input = new HashMap<String, String>();
		input.put("FFL:path", "http://localhost:8080/FFL");
		input.put("FFL:title", title);
		input.put("FFL:message", htmlbody);
		input.put("FFL:need-button", "block");
		input.put("FFL:button", "Click here");
		input.put("FFL:link-button", "http://localhost:8080/FFL/pages/index.jsp");
		sendEmail(to,subject,input);
	}
	public void sendEmail(String to, String subject,Map<String, String> input){

		
		try {
			session = Session.getDefaultInstance(properties, null);
			message = new MimeMessage(session);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setFrom(new InternetAddress(sender,"FamForLife"));
			message.setSubject("FamForLife: " + subject);
		
			URL url = new URL(path+"/src/websrc/mail.html");
			Scanner s = new Scanner(url.openStream());
			String file = "";
			while(s.hasNext()){
				file = file + s.nextLine();
			}
			
			String htmlText = readEmailFromHtml(file,input);
			System.out.println("Log htmlText: " + htmlText);
			message.setContent(htmlText, "text/html");
			s.close();
			
		} catch (AddressException e) {
			System.out.println(e.getMessage());
		} catch (MessagingException e) {
			System.out.println(e.getMessage());
		}  catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		}	catch (IOException e) {
			e.printStackTrace();
		}
 
		try {
			Transport transport = session.getTransport("smtps");
			transport.connect("smtp.gmail.com", sender, password);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			System.out.println("mail sent to:" + to);
		} catch (MessagingException e) {
			System.out.println(e.getMessage());
		}
		
	}

	
	private String readEmailFromHtml(String filePath, Map<String, String> input)
	{
	    String msg = filePath;//readContentFromFile(filePath);
	    try
	    {
	    Set<Entry<String, String>> entries = input.entrySet();
	    for(Map.Entry<String, String> entry : entries) {
	        msg = msg.replace(entry.getKey().trim(), entry.getValue().trim());
	    }
	    }
	    catch(Exception exception)
	    {
	        exception.printStackTrace();
	    }
	    return msg;
	}


	public void sendSimpleMail(String to,String subject,String text) {
		try {
			session = Session.getDefaultInstance(properties, null);
			message = new MimeMessage(session);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setFrom(new InternetAddress("bt1602gp@gmail.com","FamForLife"));
			message.setSubject("FamForLife: " + subject);
			
			System.out.println("Log htmlText: " + text);
			message.setContent(text, "text/html");
			
		} catch (AddressException e) {
			System.out.println(e.getMessage());
		} catch (MessagingException e) {
			System.out.println(e.getMessage());
		}  catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		}	
 
		try {
			Transport transport = session.getTransport("smtps");
			transport.connect("smtp.gmail.com", "bt1602gp@gmail.com", "admin@gmail");
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			System.out.println("mail sent to:" + to);
		} catch (MessagingException e) {
			System.out.println(e.getMessage());
		}
	}
}