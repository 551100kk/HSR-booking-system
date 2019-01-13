package view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.CoinController;
import controller.DBOperator;
import controller.MailSender;
import controller.OrderController;
import model.BookCondition;
import model.Order;
import model.Train;
import model.User;

@WebServlet("/checkout")
public class CheckoutView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			response.sendRedirect("login");
			return;
		}
		BookCondition bookCondition = (BookCondition) session.getAttribute("bookCondition");
		User user = (User) session.getAttribute("user");
		if (bookCondition == null) {
			response.sendRedirect("booking");
			return;
		}
		Train trainOut = (Train) session.getAttribute("trainOut");
		Train trainIn = (Train) session.getAttribute("trainIn");
		if (trainOut == null || bookCondition.isReturn() && trainIn == null) {
			response.sendRedirect("booking");
			return;
		}
		Order order = null;
		try {
			order = OrderController.createOrder(trainOut, trainIn, bookCondition, user);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("home?error=1");
			return;
		}
		session.setAttribute("trainOut", null);
		session.setAttribute("trainIn", null);
		session.setAttribute("order", order);
        request.getRequestDispatcher("checkout.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			response.sendRedirect("login");
			return;
		}
		if (session.getAttribute("order") == null) {
			response.sendRedirect("booking");
			return;
		}
		Order order = (Order) session.getAttribute("order");
		User user = (User) session.getAttribute("user");
		if (order.getTickets().isEmpty()) {
			response.sendRedirect("booking");
			return;
		}
		try {
			user = DBOperator.selectUser(user);
			if (user.getHsrcoin() - order.getPrice() < 0) {
				System.out.println("[Error] CheckoutView - Insufficient fund: " + user.getUsername());
				response.sendRedirect("booking?error=2");
				return;
			}
			order = OrderController.checkout(order);
			CoinController.updateCoin(user, order.getPrice() * -1);
			user = DBOperator.selectUser(user);
			session.setAttribute("user", user);
			System.out.println("[Success] CheckoutView - booked");
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("home?error=1");
			return;
		}
		if (order != null) {
			MailSender.sendOrderEmail(order, (User) session.getAttribute("user"));
			response.sendRedirect("home?order=" + order.getOrderID());
		}
		else
			response.sendRedirect("home?error=1");
	}
}
