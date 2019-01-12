package initializer;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.*;

import model.Constant;
import model.DiscountEntry;

public class EarlyBirdParser {
	public static String readJSON() throws FileNotFoundException {
		StringBuffer str = new StringBuffer();
		InputStream f = new FileInputStream("data/earlyDiscount.json");
		Scanner sc = new Scanner(f);
		while (sc.hasNext()) {
			str.append(sc.next() + " ");
		}
		sc.close();
		return str.toString();
	}
	
	public static ArrayList<DiscountEntry> getDiscountArray() throws FileNotFoundException {
		ArrayList<DiscountEntry> discountArray = new ArrayList<DiscountEntry>();
		String file = readJSON();
		JSONObject obj = new JSONObject(file);
		JSONArray arr = obj.getJSONArray("DiscountTrains");
		for (int i = 0; i < arr.length(); i++) {
			String trainID = arr.getJSONObject(i).getString("TrainNo");
			JSONObject disc = arr.getJSONObject(i).getJSONObject("ServiceDayDiscount");
			for (int weekday = 0; weekday < 7; weekday++) {
				String weekdayStr = Constant.daysOfWeek[weekday];
				Object discObj = disc.get(weekdayStr); 
				if (discObj.getClass() == org.json.JSONArray.class) {
					for (int j = 0; j < ((JSONArray) discObj).length(); j++) {
						double discount = ((JSONArray) discObj).getJSONObject(j).getDouble("discount");
						if (discount < 1.0e-9 || discount == 1.0) continue;
						int tickets = ((JSONArray) discObj).getJSONObject(j).getInt("tickets");
						discountArray.add(new DiscountEntry(Integer.parseInt(trainID), weekday, 2, discount, tickets));
					}
				}
				else {
					double discount = disc.getDouble(weekdayStr);
					if (discount < 1.0e-9 || discount == 1.0) continue; 
					discountArray.add(new DiscountEntry(Integer.parseInt(trainID), weekday, 2, discount, Constant.totalSeat));
				}
			}
		}
		return discountArray;
	}
}
