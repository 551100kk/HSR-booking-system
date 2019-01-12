package view;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.AuthController;
import model.User;

@WebServlet("/login")
public class LoginView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null) {
			response.sendRedirect("home");
		} else {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = new User(username, password);
		System.out.println("[Info] LoginView - username: " + user.getUsername());
		User userReturn = null;
		try {
			userReturn = AuthController.login(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (userReturn != null) {
			session.setAttribute("user", userReturn);
			response.sendRedirect("home");
		} else {
			response.sendRedirect("login?status=1");
		}
	}
}
