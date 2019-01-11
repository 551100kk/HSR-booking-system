package view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BookCondition;
import model.Date;
import model.Time;

@WebServlet("/booking")
public class BookingView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			response.sendRedirect("login");
			return;
		}
        request.getRequestDispatcher("booking.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			response.sendRedirect("login");
			return;
		}
		try {
			int fromStation = Integer.parseInt(request.getParameter("fromStation"));
			int toStation = Integer.parseInt(request.getParameter("toStation"));
			if (fromStation == toStation)
				throw new Exception("Same station");
			BookCondition bookCondition = new BookCondition(fromStation, toStation);
			bookCondition.setSeatClass(Integer.parseInt(request.getParameter("seatClass")));
			bookCondition.setSeatPreference(Integer.parseInt(request.getParameter("seatPreference")));
			bookCondition.setSearchType(Integer.parseInt(request.getParameter("searchType")));
			bookCondition.setDateOut(new Date(request.getParameter("dateOut").replace("-", "")));
			bookCondition.setDateIn(new Date(request.getParameter("dateIn").replace("-", "")));
			bookCondition.setDepartureTimeOut(new Time(request.getParameter("departureTimeOut").replace(":", "")));
			bookCondition.setDepartureTimeIn(new Time(request.getParameter("departureTimeIn").replace(":", "")));
			bookCondition.setTrainIDOut(Integer.parseInt(request.getParameter("trainIDOut")));
			bookCondition.setTrainIDIn(Integer.parseInt(request.getParameter("trainIDIn")));
			bookCondition.setPassengers(Integer.parseInt(request.getParameter("passengers")));
			bookCondition.setReturn(1 == Integer.parseInt(request.getParameter("isReturn")));
			bookCondition.setEarlyBird(1 == Integer.parseInt(request.getParameter("isEarlyBird")));			
			
			session.setAttribute("bookCondition", bookCondition);
			response.sendRedirect("selectTrain");	
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("booking");
		}
		
	}
}
