package model;

public class BookCondition {
	public int fromStation;
	public int toStation;
	public int seatClass;
	public int seatPreference;
	public Date dateOut;
	public Date dateIn;
	public Time departureTimeOut;
	public Time departureTimeIn;
	public int trainID;
	public int passengers;
	public boolean isReturn;
	public boolean isStudent;
	public boolean isEarlyBird;
	
	public BookCondition(int fromStation, int toStation) {
		super();
		this.fromStation = fromStation;
		this.toStation = toStation;
	}
}
