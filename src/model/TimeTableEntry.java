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
	private ArrayList<Discount> earlyBirdDiscount;
	private ArrayList<Discount> studentDiscount;
	
	public TimeTableEntry() {
		this.timeList = new ArrayList<Time>();
		this.earlyBirdDiscount = new ArrayList<Discount>();
		this.studentDiscount = new ArrayList<Discount>();
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
	Date getDate() {
		return date;
	}
	void setDate(Date date) {
		this.date = date;
	}
	ArrayList<Time> getTimeList() {
		return timeList;
	}
	void setTimeList(ArrayList<Time> timeList) {
		this.timeList = timeList;
	}
	ArrayList<Discount> getEarlyBirdDiscount() {
		return earlyBirdDiscount;
	}
	void setEarlyBirdDiscount(ArrayList<Discount> earlyBirdDiscount) {
		this.earlyBirdDiscount = earlyBirdDiscount;
	}
	ArrayList<Discount> getStudentDiscount() {
		return studentDiscount;
	}
	void setStudentDiscount(ArrayList<Discount> studentDiscount) {
		this.studentDiscount = studentDiscount;
	}	
}
