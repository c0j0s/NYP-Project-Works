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
		//new Main(to);

	}
	
	public void run() {
		try {

			// 設定傳送基本資訊
			String host = "smtp.gmail.com";
			String from = "bt1602gp@gmail.com";
			String user= "bt1602gp";
			String pwd= "admin@gmail";
			String port = "587";
			
			Properties props = System.getProperties();

			
			// 設定SMTP server

			props.put("mail.smtp.host", host);
			props.put("mail.smtp.auth", "true");
			//props.put("mail.smtp.port", port);
			//props.put("mail.smtp.starttls.enable", true);
			//使用ssl或tls連線
			MailSSLSocketFactory sf = new MailSSLSocketFactory();
			sf.setTrustAllHosts(true); 
			props.put("mail.imap.ssl.trust", "*");
			props.put("mail.imap.ssl.socketFactory", sf);
			//認證
			Authenticator auth = new SMTPAuthenticator(user,pwd);
			// 依據Properties建立一個Session
			Session mailSession = Session.getInstance(props, auth);
			

			// 從Session建立一個Message

			/*Message mailMessage = new MimeMessage(mailSession);
			// Set from email address
			mailMessage.setFrom(new InternetAddress(from));
			// Set to mail address
			mailMessage.setRecipient(Message.RecipientType.TO,
					new InternetAddress(to));
			// 設定標題
			mailMessage.setSubject("Hello JavaMail");
			// 設定Mail內容
			mailMessage.setText("Wellcome to  JavaMail...加油!!");*/
			
			
			MimeMessage message = new MimeMessage(mailSession);
			message.setFrom(new InternetAddress("c.junsheng@hotmail.com"));
			// System.out.println(p.getProperty("fromName","utf-8"));
			  /*new InternetAddress(
					  to)*/
			  for(int i = 0 ; i < to.size();i++)
				  message.addRecipient(Message.RecipientType.TO, to.get(i));
			  // 加入標題
			  message.setSubject(this.subject);
			  
			  message.setSentDate(new Date());
			  // 向multipart增加個別內容body
			  Multipart multipart = new MimeMultipart();

			  //設定內容本文
			  BodyPart contentPart = new MimeBodyPart();
			 //Multi
			  contentPart.setContent(content,"text/plain;charset=big5");//給BodyPart對像設置內容和格式/編碼方式
			  //純文字內容
			  //contentPart.setText(content);
			  multipart.addBodyPart(contentPart);

			  // 增加附件
			  // BodyPart messageBodyPart = new MimeBodyPart();
			  // DataSource source = new FileDataSource(affix);
			  // 增加附件的内容
			  // messageBodyPart.setDataHandler(new DataHandler(source));
			  // 附件標題
			  // 使用base64編碼,讓傳送過程內容不會變亂碼
			  // sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
			  // messageBodyPart.setFileName(MimeUtility.encodeText(filename,"BIG5","B"));
			  // multipart.addBodyPart(messageBodyPart);

			  // 將multipart放到message中
			  message.setContent(multipart);
			  // 保存郵件
			  message.saveChanges();
			
			
			Transport transport = mailSession.getTransport("smtp");
			// 傳送
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