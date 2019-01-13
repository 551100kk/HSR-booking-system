package view;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.OrderController;
import controller.TicketController;
import model.BookCondition;
import model.Order;
import model.Ticket;
import model.Train;
import model.User;

@WebServlet("/history")
public class HistoryView extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			response.sendRedirect("login");
			return;
		}
		User user = (User) session.getAttribute("user");
		ArrayList<Order> orders = null;
		try {
			orders = OrderController.searchByOrderUser(user);
		} catch (Exception e) {
			response.sendRedirect("home?error=1");
			return;
		}

		session.setAttribute("orders", orders);
        request.getRequestDispatcher("history.jsp").forward(request, response);
	}
	
	
}
