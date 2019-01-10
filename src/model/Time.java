package model;

public class Time {
	int hour;
	int min;
	
	public Time(String time) {
		while (time.length() < 4) {
			time = "0" + time;
		}
		hour = Integer.parseInt(time) / 100;
		min = Integer.parseInt(time) % 100;
	}
	
	public Time(int time) {
		hour = time / 60;
		min = time % 60;
	}
	
	public String toString() {
		return Integer.toString(hour) + Integer.toString(hour);
	}
	
	public int getMin() {
		return hour * 60 + min;
	}
	
	public Time getDuration(Time time) {
		return new Time(time.getMin() - this.getMin());
	}
}
