package common;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import com.sun.mail.util.MailSSLSocketFactory;

public class MailSSL  {
	private String content;
	
	private String subject;
	private ArrayList<InternetAddress> to;
	private Properties p;
	public MailSSL(ArrayList<InternetAddress> to,String subject,String content)
	{
		super();
		this.content = content;
		this.to = to;
		this.subject = subject;
	}
	
	public static void main(String args[])throws Exception
	{
		ArrayList<InternetAddress> to = new ArrayList<InternetAddress>();

		to.add(new InternetAddress("c.junsheng@hotmail.com"));

		MailSSL sender = new MailSSL(to,"subject","content");
		sender.run();
	

	}
	
	public void run() {
		try {

			
			String host = "smtp.gmail.com";
			String from = "bt1602gp@gmail.com";
			String user= "bt1602gp";
			String pwd= "admin@gmail";
			String port = "587";
			
			Properties props = System.getProperties();

			


			props.put("mail.smtp.host", host);
			props.put("mail.smtp.auth", "true");
			
			MailSSLSocketFactory sf = new MailSSLSocketFactory();
			sf.setTrustAllHosts(true); 
			props.put("mail.imap.ssl.trust", "*");
			props.put("mail.imap.ssl.socketFactory", sf);

			Authenticator auth = new SMTPAuthenticator(user,pwd);
	
			Session mailSession = Session.getInstance(props, auth);
			

	
			
			MimeMessage message = new MimeMessage(mailSession);
			message.setFrom(new InternetAddress("c.junsheng@hotmail.com"));
			
			  for(int i = 0 ; i < to.size();i++)
				  message.addRecipient(Message.RecipientType.TO, to.get(i));
			
			  message.setSubject(this.subject);
			  
			  message.setSentDate(new Date());
	
			  Multipart multipart = new MimeMultipart();

			  BodyPart contentPart = new MimeBodyPart();
		
			  contentPart.setContent(content,"text/plain;charset=big5");
			  multipart.addBodyPart(contentPart);

			 
			  message.setContent(multipart);
			  
			  message.saveChanges();
			
			mailSession.setDebug(true);
			Transport transport = mailSession.getTransport("smtp");
			// ‚÷ËÍ
			transport.connect(host, user, pwd);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			//Transport.send(mailMessage);

			//System.out.println("\n Mail was sent successfully.");

		} catch (Exception e) {

			e.printStackTrace();

		}

	}
	private class SMTPAuthenticator extends javax.mail.Authenticator {
		private String SMTP_AUTH_PWD;
		private String SMTP_AUTH_USER;
		public SMTPAuthenticator(String SMTP_AUTH_USER,String SMTP_AUTH_PWD)
		{
			super();
			this.SMTP_AUTH_USER = SMTP_AUTH_USER;
			this.SMTP_AUTH_PWD = SMTP_AUTH_PWD;
		}
        public PasswordAuthentication getPasswordAuthentication() {
           String username = SMTP_AUTH_USER;
           String password = SMTP_AUTH_PWD;
           System.out.println(SMTP_AUTH_USER+";"+SMTP_AUTH_PWD);
           return new PasswordAuthentication(username, password);
        }
    }
}