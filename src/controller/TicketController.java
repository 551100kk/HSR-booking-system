package controller;

import java.util.*;

import model.User;
import model.Ticket;
import model.Train;
import model.BookCondition;
import model.Seat;

public class TicketController {
	public static ArrayList<Ticket> searchByUser(User user) throws Exception{
		return DBOperator.selectTicketsByUser(user);
	}
	public static Ticket searchByTicketID(int ticketID) throws Exception{
		return DBOperator.selectTicketByID(ticketID);
	}
	public static void deleteByTicketID(int ticketID) throws Exception{
		DBOperator.deleteTicketByID(ticketID);
		return;
	}
	public static ArrayList<Ticket> createTickets(Train train, BookCondition bookCondition, User user, Boolean isReturn) throws Exception{
		ArrayList<Seat> seatAvail = DBOperator.selectAvailableSeats(train, bookCondition, isReturn);
		if(bookCondition.getPassengers() > seatAvail.size())
			return null;
		int begin = new Random().nextInt(seatAvail.size()-bookCondition.getPassengers());
		ArrayList<Seat> list = (ArrayList<Seat>) seatAvail.subList(begin, begin + bookCondition.getPassengers());
		ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
		for(int i=0;i < bookCondition.getPassengers(); i++) {
			Ticket ticket = new Ticket();
			ticket.setUsername(user.getUsername());
			ticket.setTrainID(train.getTrainID());
			ticket.setDate(isReturn ? bookCondition.getDateIn() : bookCondition.getDateOut() );
			ticket.setSeatID(list.get(i).getSeatID());
			ticket.setSeatClass(list.get(i).getSeatClass());
			ticket.setFromStation(isReturn ? bookCondition.getToStation() : bookCondition.getFromStation());
			ticket.setToStation(isReturn ? bookCondition.getFromStation() : bookCondition.getToStation());
			ticket.setDepartureTime(train.getDepartureTime());
			ticket.setArrivalTime(train.getArrivalTime());
			ticket.setDiscount(train.getDiscount());
			ticket.setDiscountType(bookCondition.isEarlyBird() ? 2 : (bookCondition.getSeatClass() == 2) ? 1 : 0);
			ticketList.add(ticket);
		}
		return ticketList;
	}
}