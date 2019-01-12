package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import model.BookCondition;
import model.Order;
import model.Ticket;
import model.Train;
import model.User;

public class OrderController {
	public static Order createOrder(Train trainOut, Train trainIn, BookCondition bookCondition, User user) throws Exception {
		Order order = new Order();
		ArrayList<Ticket> ticketList = TicketController.createTickets(trainOut, bookCondition, user, false);
		if (ticketList == null) {
			return null;
		}
		
		if (bookCondition.isReturn()) {
			ArrayList<Ticket> inboundTicketList = TicketController.createTickets(trainIn, bookCondition, user, true);
			if (inboundTicketList == null) {
				return null;
			}
			for (Ticket t : inboundTicketList) {
				ticketList.add(t);
			}
		}
		order.setTickets(ticketList);
		order.setUsername(user.getUsername());
		
		return order;
	}
	
	synchronized public static Order checkout(Order order) throws Exception {
		DBOperator.insertOrder(order);
		int orderID = (DBOperator.selectOrderByUser(new User(order.getUsername(), null))).get(0).getOrderID();
		order.setOrderID(orderID);
		try {
			DBOperator.insertTickets(order.getTickets(), orderID);
		} catch (SQLException e) {
			return null;
		}
		return order;
	}
	
	public static Order searchByOrderID(int orderID) throws Exception {
		return DBOperator.selectOrderByID(orderID);
	}
	
	public static void updateOrder(ArrayList<Integer> deleteIDs) throws SQLException {
		for (int id : deleteIDs) {
			DBOperator.deleteTicketByID(id);
		}
	}
	
	public static ArrayList<Order> searchByOrderUser(User user) throws Exception {
		return DBOperator.selectOrderByUser(user);
	}
	
	public static void deleteByOrderID(int orderID) throws SQLException {
		DBOperator.deleteOrderByID(orderID);
	}
}
