package model;

import java.io.Serializable;

public class Train implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int trainID;
	private double discount;
	private Time departureTime;
	private Time arrivalTime;
	
	public Train() {
		super();
	}
	
	public Train(int trainID, double discount, Time departureTime, Time arrivalTime) {
		super();
		this.trainID = trainID;
		this.discount = discount;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
	}

	public Time getDuration() {
		return getDepartureTime().getDuration(getArrivalTime());
	}

	public int getTrainID() {
		return trainID;
	}

	public void setTrainID(int trainID) {
		this.trainID = trainID;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
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
}
