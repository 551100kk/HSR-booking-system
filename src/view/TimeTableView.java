package view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.TimeTableController;
import model.Constant;
import model.Date;

@WebServlet("/timetable")
public class TimeTableView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			response.sendRedirect("login");
			return;
		}
		String dateStr = request.getParameter("date");
		request.setAttribute("station", Constant.stationChineseName);
		if (dateStr != null && dateStr.length() == 10) {
			try {
				Date date = new Date(dateStr.replace("-", ""));
				if (date.getDuration(new Date()) > 0) {
					throw new Exception("Booking date error");
				}
				request.setAttribute("date", date.getDisplayDate());
				request.setAttribute("dayofweek", Constant.WEEKDAYS[date.getDayOfWeek()]);
				TimeTableController.updateTimeTable(date);
				request.setAttribute("timeTableEntryList", TimeTableController.searchTimeTableEntries(date));
			} catch (Exception ignore) {
				ignore.printStackTrace();
			}
			
		}
        request.getRequestDispatcher("timetable.jsp").forward(request, response);
	}
}
