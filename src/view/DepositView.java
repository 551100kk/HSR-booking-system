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
import model.User;

@WebServlet("/deposit")
public class DepositView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			response.sendRedirect("login");
			return;
		}
        request.getRequestDispatcher("deposit.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			response.sendRedirect("login");
			return;
		}
		String serialcode = request.getParameter("serialcode");
		long amount = 0;
		if (serialcode.equals("LADA1000")) {
			amount += 1000;
		} else if (serialcode.equals("LADA5000")) {
			amount += 5000;
		} else if (serialcode.equals("TWVALUE")) {
			amount -= 1000;
		} else if (serialcode.equals("HANDSOMECK")) {
			amount += 10000;
		}else {
			response.sendRedirect("deposit?error=1");
			return;
		}
		
		User user = (User) session.getAttribute("user");
		try {
			CoinController.updateCoin(user, amount);
			user = DBOperator.selectUser(user);
			session.setAttribute("user", user);
			System.out.println("[Success] DepositView - " + user.getUsername() + ", " + user.getHsrcoin() + ", " + amount);
		} catch (Exception e) {
			System.out.println("[Error] DepositView - " + user.getUsername() + ", " + user.getHsrcoin());
			response.sendRedirect("deposit?error=1");
			e.printStackTrace();
			return;
		}
		response.sendRedirect("deposit?success=1");
	}
}
