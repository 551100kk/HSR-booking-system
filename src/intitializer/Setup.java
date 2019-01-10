package intitializer;

import controller.SeatController;

public class Setup {

	public static void main(String[] args) {
		try {
			InitializeDBOperator.createDataBase();
			InitializeDBOperator.createTables();
			InitializeDBOperator.InsertSeats(SeatController.getAllSeats());
			System.out.println("Successed!");
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
}
