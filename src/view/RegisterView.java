package view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.AuthController;
import model.User;

@WebServlet("/register")
public class RegisterView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phone");
		User user = new User(username, password);
		user.setEmail(email);
		user.setPhoneNumber(phoneNumber);
		System.out.println("[Info] RegisterView - username: " + user.getUsername());
		System.out.println("[Info] RegisterView - email: " + user.getEmail());
		if (AuthController.register(user)) {
			session.setAttribute("user", user);
			response.sendRedirect("home");
		} else {
			response.sendRedirect("login?status=2");
		}
	}
}
