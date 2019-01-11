package model;

public class Ticket {
	private int ticketID;
	private String username;
	private int trainID;
	private Date date;
	private String seatID;
	private int seatClass;
	private int fromStation;
	private int toStation;
	private Time departureTime;
	private Time arrivalTime;
	private double discount = 1.0;
	private int discountType = 0;	// 0: non, 1: student, 2: earlyBird
	
	public int getPrice() {
		int priceMatrix[][];
		if (getSeatClass() == 0)
			priceMatrix = Constant.standardPrice;
		else
			priceMatrix = Constant.businessPrice;
		int station1 = Math.min(getFromStation(), getToStation());
		int station2 = Math.max(getFromStation(), getToStation());
		return (int) (priceMatrix[station1][station2] * getDiscount());
	}
	
	public int getDirection() {
		return (getFromStation() < getToStation() ? 0 : 1);
	}
	
	public Time getDuration() {
		return getDepartureTime().getDuration(getArrivalTime());
	}

	public int getTicketID() {
		return ticketID;
	}

	public void setTicketID(int ticketID) {
		this.ticketID = ticketID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getTrainID() {
		return trainID;
	}

	public void setTrainID(int trainID) {
		this.trainID = trainID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getSeatID() {
		return seatID;
	}

	public void setSeatID(String seatID) {
		this.seatID = seatID;
	}

	public int getSeatClass() {
		return seatClass;
	}

	public void setSeatClass(int seatClass) {
		this.seatClass = seatClass;
	}

	public int getFromStation() {
		return fromStation;
	}

	public void setFromStation(int fromStation) {
		this.fromStation = fromStation;
	}

	public int getToStation() {
		return toStation;
	}

	public void setToStation(int toStation) {
		this.toStation = toStation;
	}

	public Time getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Time departureTime) {
		this.departureTime = departureTime;
	}

	public Time getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Time arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public int getDiscountType() {
		return discountType;
	}

	public void setDiscountType(int discountType) {
		this.discountType = discountType;
	}
}
