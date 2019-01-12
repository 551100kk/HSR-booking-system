package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int orderID;
	private ArrayList<Ticket> tickets;
	private String username;
	private java.util.Date createTime;
	
	public Order() {
		this.tickets = new ArrayList<Ticket>();
	}
	
	public int getPrice() {
		int total = 0;
		for (Ticket ticket: getTickets()) {
			total += ticket.getPrice();
		}
		return total;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public ArrayList<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(ArrayList<Ticket> tickets) {
		this.tickets = tickets;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
}
