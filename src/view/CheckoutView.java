package view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BookCondition;
import model.Order;
import model.Train;

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
		Order order = new Order();
		// TODO
		
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
		// TODO
		boolean done = true;
		if (done)
			response.sendRedirect("home?order=187");
		else
			response.sendRedirect("home?error=187");
	}
}
