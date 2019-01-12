package controller;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

import model.Order;
import model.User;

public class MailSender {
	private static final String host = "smtp.gmail.com";
	private static final int port = 587;
	private static final String username = "ooad.2018.lada@gmail.com";
	private static final String password = "lada.2018.ooad";

	public static void sendOrderEmail(Order order, User user) {
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", port);
		
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("sender@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
			message.setSubject("[高鐵訂票] 訂單編號 - " + order.getOrderID());
			message.setText("Dear " + user.getUsername() + ",\n");

			Transport transport = session.getTransport("smtp");
			transport.connect(host, port, username, password);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] str) {
		Order order = new Order();
		order.setOrderID(8787);
		User user = new User("Lada", "dada");
		user.setEmail("551100kk@gmail.com");
		sendOrderEmail(order, user);
	}
}
