package model;

public class Seat {
	public static final int BUSS_CABIN = 5;
	public static final int TOTAL_CABIN = 9;
	public static final int TOTAL_SEATS = 733; 
	public static final int CABIN_SEATS[] = {63, 96, 88, 96, 83, 66, 57, 96 ,88};
	
	public String seatID;
	public int seatClass;
	public int seatPreference;
	
	public Seat(String seatID, int seatClass, int seatPreference) {
		super();
		this.seatID = seatID;
		this.seatClass = seatClass;
		this.seatPreference = seatPreference;
	}
}
