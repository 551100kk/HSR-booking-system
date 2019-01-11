package intitializer;

public class Setup {

	public static void main(String[] args) {
		try {
			InitializeDBOperator.createDataBase();
			InitializeDBOperator.createTables();
			InitializeDBOperator.insertSeats(SeatController.getAllSeats());
			InitializeDBOperator.insertDiscounts(EarlyBirdParser.getDiscountArray());
			InitializeDBOperator.insertDiscounts(StudentParser.getDiscountArray());
			System.out.println("Successed!");
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
}
