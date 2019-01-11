package model;

import java.util.ArrayList;

public class Order {
	private int orderId;
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

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
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
