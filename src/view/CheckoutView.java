package view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		Order order = null;
		try {
			order = OrderController.checkout((Order) session.getAttribute("order"));
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("home?error=1");
			return;
		}
		if (order != null)
			response.sendRedirect("history?order=" + order.getOrderID());
		else
			response.sendRedirect("home?error=1");
	}
}
