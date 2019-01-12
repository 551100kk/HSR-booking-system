package view;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Time;
import model.Train;

@WebServlet("/selectTrain")
public class SelectTrainView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			response.sendRedirect("login");
			return;
		}

		ArrayList<Train> trainListOut = new ArrayList<Train>();
		ArrayList<Train> trainListIn = new ArrayList<Train>();

		trainListOut.add(new Train(
				165,
				0.8,
				new Time("2120"),
				new Time("2218")
			));
		trainListOut.add(new Train(
				187,
				0,
				new Time("2320"),
				new Time("2358")
			));
		trainListIn.add(new Train(
				213,
				0.7,
				new Time("1234"),
				new Time("1250")
			));
		trainListIn.add(new Train(
				217,
				0.7,
				new Time("1534"),
				new Time("1650")
			));

		session.setAttribute("trainListOut", trainListOut);
		session.setAttribute("trainListIn", trainListIn);
        request.getRequestDispatcher("selectTrain.jsp").forward(request, response);
	}
}
