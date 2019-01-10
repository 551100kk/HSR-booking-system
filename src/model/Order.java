package model;

import java.util.ArrayList;

public class Order {
	public int orderId;
	public ArrayList<Ticket> tickets;
	public String username;
	public java.util.Date createTime;
	
	public int getPrice() {
		int total = 0;
		for (Ticket ticket: tickets) {
			total += ticket.getPrice();
		}
		return total;
	}
}
