package model;

import java.io.Serializable;

public class BookCondition implements Serializable {
	private static final long serialVersionUID = 1L;
	private int fromStation;
	private int toStation;
	private int seatClass;
	private int seatPreference;
	private int searchType; // 0: by time, 1: by trainID
	private Date dateOut;
	private Date dateIn;
	private Time departureTimeOut;
	private Time departureTimeIn;
	private int trainIDOut;
	private int trainIDIn;
	private int passengers;
	private boolean isReturn;
	private boolean isEarlyBird;

	public BookCondition(int fromStation, int toStation) {
		super();
		this.setFromStation(fromStation);
		this.setToStation(toStation);
	}

	public boolean isValid() {
		if (fromStation < 0 || fromStation > Constant.stationEnglishName.length)
			return false;
		if (toStation < 0 || toStation > Constant.stationEnglishName.length)
			return false;
		if (seatClass < 0 || seatClass > Constant.ticketType.length)
			return false;
		if (seatPreference < 0 || seatPreference > Constant.seatType.length)
			return false;
		if (searchType != 0 && searchType != 1)
			return false;
		if (isReturn) {
			if (dateOut.getDuration(dateIn) < 0)
				return false;
			else if (dateOut.getDuration(dateIn) == 0) {
				if (departureTimeOut.getDuration(departureTimeIn).getMin() < 0)
					return false;
			}	
		}
		if (passengers > Constant.maxPassengers || passengers < 1)
			return false;
		return true;
	}

	public int getDirection() {
		if (fromStation < toStation)
			return 0;
		else
			return 1;
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

	public int getTrainIDOut() {
		return trainIDOut;
	}

	public void setTrainIDOut(int trainIDOut) {
		this.trainIDOut = trainIDOut;
	}

	public int getTrainIDIn() {
		return trainIDIn;
	}

	public void setTrainIDIn(int trainIDIn) {
		this.trainIDIn = trainIDIn;
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

	public boolean isEarlyBird() {
		return isEarlyBird;
	}

	public void setEarlyBird(boolean isEarlyBird) {
		this.isEarlyBird = isEarlyBird;
	}

	public int getSearchType() {
		return searchType;
	}

	public void setSearchType(int searchType) {
		this.searchType = searchType;
	}
}
