package controller;

import java.util.*;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import model.Date;
import model.Time;
import model.TimeTableEntry;

public class TimeTableController {
	public static ArrayList<TimeTableEntry> getTimeTableList(Date date, int dir) {
		ArrayList<TimeTableEntry> timeTableList = new ArrayList<TimeTableEntry>();
		try {
			Document doc = Jsoup.connect("http://www.thsrc.com.tw/tw/TimeTable/DailyTimeTable/" + date).get();
			Elements table = doc.select("table[bgcolor$=#CCCCCC]");
			Elements down_tables = table.get(dir).getElementsByTag("tr");
			for (Element row: down_tables) {
				// get train_id
				String trainID = row.getElementsByClass("text_orange_link").html();
				if (trainID.equals("")) continue;
				
				// get time
				//System.out.println("Train id: " + train_id);
				Elements stations = row.getElementsByClass("text_grey2");
	
				TimeTableEntry timeTableEntry = new TimeTableEntry();
				timeTableEntry.setDate(date);
				timeTableEntry.setDirection(dir);
				timeTableEntry.setTrainID(Integer.parseInt(trainID));
				ArrayList<Time> timeList = timeTableEntry.getTimeList();

				for (Element station: stations) {
					String timeStr = station.html();
					if (timeStr.equals("")) timeStr = "-1";
					else timeStr = timeStr.replace(":", "");
					timeList.add(new Time(timeStr));
				}
				
				if (dir == 1) {
					Collections.reverse(timeList);
				}
				timeTableList.add(timeTableEntry);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return timeTableList;
	}
	
	public static void updateDate(Date date) throws Exception {
		System.out.println("Parsing date " + date);
		if (DBOperator.selectTimeTableEntry(date).isEmpty()) {
			System.out.println("Updating date " + date);
			for (TimeTableEntry timeTableEntry: getTimeTableList(date, 0)) {
				DBOperator.insertTimeTableEntry(timeTableEntry);
			}
			for (TimeTableEntry timeTableEntry: getTimeTableList(date, 1)) {
				DBOperator.insertTimeTableEntry(timeTableEntry);
			}
		}
		System.out.println("Successed");
	}
	
	public static void main(String[] str) throws Exception {
		updateDate(new Date());
	}
}
