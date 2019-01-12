package controller;
 
import java.util.*;
 
import model.Train;
 
import model.BookCondition;
 
public class TrainController {
	public static ArrayList<Train> searchTrainByCondition(BookCondition bookcondition, boolean isInbound) throws Exception{
		return DBOperator.selectTrain(bookcondition, isInbound);
	}
}