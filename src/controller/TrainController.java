package controller;
 
import java.util.*;
 
import model.Train;
 
import model.BookCondition;
 
public class TrainController {
	public static ArrayList<Train> searchTrainByCondition(BookCondition bookCondition, boolean isInbound) throws Exception{
		
		TimeTableController.updateTimeTable(bookCondition.getDateOut());
		if (isInbound) {
			TimeTableController.updateTimeTable(bookCondition.getDateIn());
		}
		return DBOperator.selectTrain(bookCondition, isInbound);
	}
}