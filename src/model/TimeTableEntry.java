package model;

import java.util.ArrayList;

public class TimeTableEntry {
	public int trainID;
	public int direction;
	Date date;
	ArrayList<Time> timeList;
	ArrayList<Double> earlyBirdDiscount;
	ArrayList<Double> studentDiscount;
}
