package model;

public class DiscountEntry {
	private int trainID;
	private int dayOfWeek;
	private int discountType;	// 0: non, 1: student, 2: earlyBird
	private double discount;
	private int count;
	
	public DiscountEntry(int trainID, int dayOfWeek, int discountType, double discount, int count) {
		super();
		this.trainID = trainID;
		this.dayOfWeek = dayOfWeek;
		this.discountType = discountType;
		this.discount = discount;
		this.count = count;
	}
	
	public int getTrainID() {
		return trainID;
	}
	public void setTrainID(int trainID) {
		this.trainID = trainID;
	}
	public int getWeekday() {
		return dayOfWeek;
	}
	public void setWeekday(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public int getDiscountType() {
		return discountType;
	}
	public void setDiscountType(int discountType) {
		this.discountType = discountType;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
