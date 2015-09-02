package mail;

import java.util.*;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;


public class SendEmail {


	public static void main(String[] args) throws InterruptedException {

		//Thread.sleep(10*1000);

		final String username = "";
		final String password = "";

		Properties props = new Properties();
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("yoyodinoop@gmail.com"));
			message.setSubject("security alert");
			message.setText("someone login to ur system");
			MimeBodyPart messageBodyPart = new MimeBodyPart();

			Multipart multipart = new MimeMultipart();

			messageBodyPart = new MimeBodyPart();
			TakePicture.takePic();
			Thread.sleep(1000);
			String file = "/home/dinoop/theif.jpeg";
			String fileName = "theif.jpeg";
			DataSource source = new FileDataSource(file);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(fileName);
			//messageBodyPart.setText("someone login to ur system..");
			multipart.addBodyPart(messageBodyPart);

			message.setContent(multipart);

			System.out.println("Sending");
			while(true){
				try{
					Transport.send(message);
					break;
				}catch( Exception e){
					System.out.println(e.getCause());
					Thread.sleep(5*1000);
				}
			}
			System.out.println("Done");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
