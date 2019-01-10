package controller;

import java.util.ArrayList;

import model.Seat;

public class SeatController {
	
	public static ArrayList<Seat> getAllSeats() {
		ArrayList<Seat> seatList = new ArrayList<Seat>();
		for (int cabin = 1; cabin <= Seat.TOTAL_CABIN; cabin++) {
			int isBusiness = (cabin == 5 ? 1 : 0);
			for (int row = 1; row <= 18; row++) {
				for (int pos = 0; pos < 5; pos++) {
					int preference = 0;
					if (pos == 0 || pos == 4) preference = 1;
					if (pos == 2 || pos == 3) preference = 2;
					String seatID = String.format("%02d%02d%c", cabin, row, 'A' + pos);
					seatList.add(new Seat(seatID, isBusiness, preference));
				}
			}
		}
		return seatList;
	}
}
