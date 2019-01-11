package model;

public class Seat {
	public static final int BUSS_CABIN = 5;
	public static final int TOTAL_CABIN = 9;
	public static final int TOTAL_SEATS = 733; 
	public static final int CABIN_SEATS[] = {63, 96, 88, 96, 83, 66, 57, 96 ,88};
	
	private String seatID;
	private int seatClass;
	private int seatPreference;
	
	public Seat(String seatID, int seatClass, int seatPreference) {
		super();
		this.setSeatID(seatID);
		this.setSeatClass(seatClass);
		this.setSeatPreference(seatPreference);
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

	public int getSeatPreference() {
		return seatPreference;
	}

	public void setSeatPreference(int seatPreference) {
		this.seatPreference = seatPreference;
	}
}
