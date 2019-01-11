package model;

public class Time {
	private int hour;
	private int min;
	
	public Time(String time) {
		while (time.length() < 4) {
			time = "0" + time;
		}
		setHour(Integer.parseInt(time) / 100);
		setMin(Integer.parseInt(time) % 100);
	}
	
	public Time(int time) {
		setHour(time / 60);
		setMin(time % 60);
	}
	
	public String toString() {
		return Integer.toString(getHour()) + Integer.toString(getHour());
	}
	
	public int getMin() {
		return getHour() * 60 + min;
	}
	
	public Time getDuration(Time time) {
		return new Time(time.getMin() - this.getMin());
	}

	int getHour() {
		return hour;
	}

	void setHour(int hour) {
		this.hour = hour;
	}

	void setMin(int min) {
		this.min = min;
	}
}
