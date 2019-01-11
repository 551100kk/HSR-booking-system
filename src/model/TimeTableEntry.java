package model;

import java.util.ArrayList;

public class TimeTableEntry {
	public static class Discount {
		private double value;
		private int count;
		double getValue() {
			return value;
		}
		void setValue(double value) {
			this.value = value;
		}
		int getCount() {
			return count;
		}
		void setCount(int count) {
			this.count = count;
		}
	}
	
	private int trainID;
	private int direction;
	private Date date;
	private ArrayList<Time> timeList;
	
	public TimeTableEntry() {
		this.timeList = new ArrayList<Time>();
	}
	
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
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
		try {
			this.date = new Date(date.toString());
		} catch (Exception ignore) {}
	}
	public ArrayList<Time> getTimeList() {
		return timeList;
	}
	public void setTimeList(ArrayList<Time> timeList) {
		this.timeList = timeList;
	}
}
