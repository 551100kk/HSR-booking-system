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

import controller.CoinController;
import controller.DBOperator;
import controller.OrderController;
import controller.TicketController;
import model.BookCondition;
import model.Order;
import model.Ticket;
import model.Train;
import model.User;

@WebServlet("/delete")
public class DeleteView extends HttpServlet{
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
        request.getRequestDispatcher("delete.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        

            String[] ids = request.getParameterValues("deleteTicket");
            HashSet<Integer> toDelete = new HashSet<Integer>();
            for(String id: ids){
                toDelete.add(Integer.parseInt(id));
            }
            
            for (int i=0; i<orders.size(); i++){
                ArrayList<Ticket> tickets = orders.get(i).getTickets();
                for (int j=0; j<tickets.size(); j++){
                    if(toDelete.contains(tickets.get(j).getTicketID())){
                        try {
                        	long amount = tickets.get(j).getPrice();
                            TicketController.deleteByTicketID((int)tickets.get(j).getTicketID());
                            user = DBOperator.selectUser(user);
                            CoinController.updateCoin(user, amount);
                            user = DBOperator.selectUser(user);
                            session.setAttribute("user", user);
                        } catch (Exception e) {
                            response.sendRedirect("home?error=1");
                            return;
                        }
                    }
                }
                try {
                    if (OrderController.searchByOrderID(orders.get(i).getOrderID()).getTickets().isEmpty()){
                        OrderController.deleteByOrderID(orders.get(i).getOrderID());
                    }
                } catch (Exception e) {
                    response.sendRedirect("home?error=1");
                    return;
                }
            }
            response.sendRedirect("history");
        }
    
}
