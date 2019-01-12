package view;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.TrainController;
import model.BookCondition;
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
		BookCondition bookCondition = (BookCondition) session.getAttribute("bookCondition");
		if (bookCondition == null) {
			response.sendRedirect("booking");
			return;
		}
		ArrayList<Train> trainListOut;
		ArrayList<Train> trainListIn;
		try {
			trainListOut = TrainController.searchTrainByCondition(bookCondition, false);
			trainListIn = TrainController.searchTrainByCondition(bookCondition, bookCondition.isReturn());
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("booking?error=1");
			return;
		}
		
		if (trainListOut.size() == 0 ||  bookCondition.isReturn() && trainListIn.size() == 0) {
			response.sendRedirect("booking?error=1");
			return;
		}
		
		session.setAttribute("trainListOut", trainListOut);
		session.setAttribute("trainListIn", trainListIn);
        request.getRequestDispatcher("selectTrain.jsp").forward(request, response);
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			response.sendRedirect("login");
			return;
		}

		BookCondition bookCondition = (BookCondition) session.getAttribute("bookCondition");
		ArrayList<Train> trainListOut = (ArrayList<Train>) session.getAttribute("trainListOut");
		ArrayList<Train> trainListIn = (ArrayList<Train>) session.getAttribute("trainListIn");
		session.setAttribute("trainListOut", null);
		session.setAttribute("trainListIn", null);
		try {
			int outTrainID = Integer.parseInt(request.getParameter("outTrainID"));
			if (outTrainID >= trainListOut.size() || outTrainID < 0) {
				throw new Exception("OutTrainID out of bound");
			}
			session.setAttribute("trainOut", trainListOut.get(outTrainID));
			
			if (bookCondition.isReturn()) {
				int inTrainID = Integer.parseInt(request.getParameter("outTrainID"));
				if (inTrainID >= trainListIn.size() || inTrainID < 0) {
					throw new Exception("InTrainID out of bound");
				}
				session.setAttribute("trainIn", trainListOut.get(inTrainID));
			}
			
			response.sendRedirect("checkout");	
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("selectTrain");
		}
	}
}
