package model;

public class Ticket {
	public int ticketID;
	public String username;
	public int trainID;
	public Date date;
	public String seatID;
	public int seatClass;
	public int fromStation;
	public int toStation;
	public Time departureTime;
	public Time arrivalTime;
	public double discount = 1.0;
	public int discountType = 0;	// 0: non, 1: student, 2: earlyBird
	
	public int getPrice() {
		int priceMatrix[][];
		if (seatClass == 0)
			priceMatrix = Constant.standardPrice;
		else
			priceMatrix = Constant.businessPrice;
		int station1 = Math.min(fromStation, toStation);
		int station2 = Math.max(fromStation, toStation);
		return (int) (priceMatrix[station1][station2] * discount);
	}
	
	public int getDirection() {
		return (fromStation < toStation ? 0 : 1);
	}
	
	public Time getDuration() {
		return departureTime.getDuration(arrivalTime);
	}
}
