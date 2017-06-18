package common;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
 
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
			MimeMultipart multipart = new MimeMultipart();
            MimeBodyPart messageBodyPart = new MimeBodyPart();
			
			Map<String, String> input = new HashMap<String, String>();
			input.put("FFL:title", subject);
			input.put("FFL:preview", subject);
			input.put("FFL:title", subject);
			input.put("FFL:message", emailBody);
			input.put("FFL:button", "http://localhost:8080/FFL/pages/index.jsp");
			input.put("FFL:buttonLink", "Click here");
			
			String htmlText = readEmailFromHtml("../../../pages/parts/email.html",input);
            messageBodyPart.setContent(htmlText, "text/html");
			
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
	
	protected static String readEmailFromHtml(String filePath, Map<String, String> input)
	{
	    String msg = readContentFromFile(filePath);
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

	private static String readContentFromFile(String fileName)
	{
	    StringBuffer contents = new StringBuffer();
	    
	    try {
	      //use buffering, reading one line at a time
	      BufferedReader reader =  new BufferedReader(new FileReader(fileName));
	      try {
	        String line = null; 
	        while (( line = reader.readLine()) != null){
	          contents.append(line);
	          contents.append(System.getProperty("line.separator"));
	        }
	      }
	      finally {
	          reader.close();
	      }
	    }
	    catch (IOException ex){
	      ex.printStackTrace();
	    }
	    return contents.toString();
	}
}