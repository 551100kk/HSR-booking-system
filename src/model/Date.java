package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@SuppressWarnings("serial")
public class Date extends java.util.Date {
	public static SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	
	public Date(String date) throws ParseException {
		super(formatter.parse(date).getTime());
	}
	
	public Date() {
		super();
	}
	
	public int getDuration(Date date) {
		long diff = date.getTime() - this.getTime();
		return (int) (diff / (1000 * 60 * 60 * 24));
	}
	
	public String toString() {
		return formatter.format(this);
	}
}
