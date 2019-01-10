package model;

public class Train {
	public int trainID;
	public Date date;
	public double discount;
	public Time departureTime;
	public Time arrivalTime;
	
	public Time getDuration() {
		return departureTime.getDuration(arrivalTime);
	}
}
