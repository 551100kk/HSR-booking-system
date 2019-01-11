package model;

public class BookCondition {
	private int fromStation;
	private int toStation;
	private int seatClass;
	private int seatPreference;
	private Date dateOut;
	private Date dateIn;
	private Time departureTimeOut;
	private Time departureTimeIn;
	private int trainID;
	private int passengers;
	private boolean isReturn;
	private boolean isStudent;
	private boolean isEarlyBird;
	
	public BookCondition(int fromStation, int toStation) {
		super();
		this.setFromStation(fromStation);
		this.setToStation(toStation);
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

	public Date getDateOut() {
		return dateOut;
	}

	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
	}

	public Date getDateIn() {
		return dateIn;
	}

	public void setDateIn(Date dateIn) {
		this.dateIn = dateIn;
	}

	public Time getDepartureTimeOut() {
		return departureTimeOut;
	}

	public void setDepartureTimeOut(Time departureTimeOut) {
		this.departureTimeOut = departureTimeOut;
	}

	public Time getDepartureTimeIn() {
		return departureTimeIn;
	}

	public void setDepartureTimeIn(Time departureTimeIn) {
		this.departureTimeIn = departureTimeIn;
	}

	public int getTrainID() {
		return trainID;
	}

	public void setTrainID(int trainID) {
		this.trainID = trainID;
	}

	public int getPassengers() {
		return passengers;
	}

	public void setPassengers(int passengers) {
		this.passengers = passengers;
	}

	public boolean isReturn() {
		return isReturn;
	}

	public void setReturn(boolean isReturn) {
		this.isReturn = isReturn;
	}

	public boolean isStudent() {
		return isStudent;
	}

	public void setStudent(boolean isStudent) {
		this.isStudent = isStudent;
	}

	public boolean isEarlyBird() {
		return isEarlyBird;
	}

	public void setEarlyBird(boolean isEarlyBird) {
		this.isEarlyBird = isEarlyBird;
	}
}
