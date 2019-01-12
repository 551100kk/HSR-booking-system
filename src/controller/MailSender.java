package controller;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

import model.Constant;
import model.Order;
import model.Ticket;
import model.User;

public class MailSender {
	private static final String host = "smtp.gmail.com";
	private static final int port = 587;
	private static final String username = "ooad.2018.lada@gmail.com";
	private static final String password = "lada.2018.ooad";

	public static void sendOrderEmail(Order order, User user) {
		new Thread(new Runnable() {

			@Override
			public void run() {
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

					Ticket ticket = order.getTickets().get(0);
					String date = ticket.getDate().getDisplayDate();
					String fromStaion = Constant.stationChineseName[ticket.getFromStation()];
					String toStaion = Constant.stationChineseName[ticket.getToStation()];
					message.setText("Dear " + user.getUsername() + ",\n" + "感謝您訂購" + date + "從" + fromStaion + "到"
							+ toStaion + "的車票\n" + "已收到您的帳款" + order.getPrice() + "元新台幣\n" + "期待再次為您服務!");

					Transport transport = session.getTransport("smtp");
					transport.connect(host, port, username, password);

					Transport.send(message);
					System.out.println("[Success] Email: OrderID: " + order.getOrderID());
				} catch (MessagingException e) {
					System.out.println("[Error] Email: OrderID: " + order.getOrderID());
					e.printStackTrace();
				}
			}
		}).start();

	}
}
