package initializer;

import java.sql.*;
import java.util.ArrayList;

import model.DiscountEntry;
import model.Seat;

public class InitializeDBOperator {
	// connection information
	private static final String dbName = "HighSpeedRail";
	private static final String connectionArguments = "?useSSL=false&serverTimezone=UTC";
	private static final String createDBUrl = "jdbc:mysql://127.0.0.1:3306/" + connectionArguments;
	private static final String queryDBUrl = "jdbc:mysql://127.0.0.1:3306/" + dbName + connectionArguments;
	private static final String username = "root";
	private static final String password = "root";
	
	// SQL statement
	private static final String createDatabaseSql = "CREATE DATABASE IF NOT EXISTS " + dbName;
	private static final String createUserTableSql = "CREATE TABLE IF NOT EXISTS User (\n" + 
			"    username varchar(64) NOT NULL PRIMARY KEY,\n" + 
			"    password varchar(64) NOT NULL,\n" + 
			"    email varchar(64),\n" + 
			"    phoneNumber int\n" + 
			"); ";
	private static final String createTrainTableSql = "CREATE TABLE IF NOT EXISTS TimeTable (\n" + 
			"    trainID int NOT NULL,\n" + 
			"    date int NOT NULL,\n" +  
			"    dayOfWeek int NOT NULL,\n" + 
			"    direction int NOT NULL,\n" + 
			"    Nangang int NOT NULL,\n" + 
			"    Taipei int NOT NULL,\n" + 
			"    Banciao int NOT NULL,\n" + 
			"    Taoyuan int NOT NULL,\n" + 
			"    Hsinchu int NOT NULL,\n" + 
			"    Miaoli int NOT NULL,\n" + 
			"    Taichung int NOT NULL,\n" + 
			"    Changhua int NOT NULL,\n" + 
			"    Yunlin int NOT NULL,\n" + 
			"    Chiayi int NOT NULL,\n" + 
			"    Tainan int NOT NULL,\n" + 
			"    Zuoying int NOT NULL,\n" + 
			"    PRIMARY KEY (trainID, date, dayOfWeek, direction)\n" + 
			");";
	private static final String createDiscountTableSql = "CREATE TABLE IF NOT EXISTS Discount (\n" + 
			"    trainID int NOT NULL,\n" + 
			"    dayOfWeek int NOT NULL,\n" + 
			"    discountType int NOT NULL,\n" + 
			"    discount float,\n" + 
			"    count int,\n" + 
			"    PRIMARY KEY (trainID, dayOfWeek, discountType, discount)\n" + 
			");";
	private static final String createSeatTableSql = "CREATE TABLE IF NOT EXISTS Seat (\n" + 
			"    seatID varchar(8) NOT NULL,\n" + 
			"    seatClass int NOT NULL,\n" + 
			"    seatPreference int NOT NULL,\n" + 
			"    PRIMARY KEY (seatID)\n" + 
			");";
	private static final String createOrderTableSql = "CREATE TABLE IF NOT EXISTS UserOrder (\n" + 
			"    orderID int NOT NULL PRIMARY KEY AUTO_INCREMENT,\n" + 
			"    username varchar(64) NOT NULL,\n" + 
			"    createTime datetime NOT NULL,\n" + 
			"    FOREIGN KEY (username) REFERENCES User(username)\n" + 
			");";
	private static final String createTicketTableSql = "CREATE TABLE IF NOT EXISTS Ticket (\n" + 
			"    ticketID int NOT NULL PRIMARY KEY AUTO_INCREMENT,\n" + 
			"    username varchar(64) NOT NULL,\n" + 
			"    orderID int NOT NULL,\n" + 
			"    seatID varchar(8) NOT NULL,\n" + 
			"    trainID int NOT NULL,\n" + 
			"    date int NOT NULL,\n" + 
			"    fromStation int NOT NULL,\n" + 
			"    toStation int NOT NULL,\n" + 
			"    departureTime int NOT NULL,\n" + 
			"    arrivalTime int NOT NULL,\n" + 
			"    discountType int NOT NULL,\n" + 
			"    discount float,\n" + 
			"    FOREIGN KEY (username) REFERENCES User(username),\n" + 
			"    FOREIGN KEY (orderID) REFERENCES UserOrder(orderID),\n" + 
			"    FOREIGN KEY (trainID, date) REFERENCES TimeTable(trainID, date),\n" +
			"    unique(date, trainID, SeatID)\n" + 
			");";
	private static final String insertSeatSql = "INSERT INTO Seat (seatID, seatClass, seatPreference)\n" + 
			"VALUES (?, ?, ?)";
	private static final String insertDiscountSql = "INSERT INTO Discount (trainID, dayOfWeek, discountType, discount, count)\n" + 
			"VALUES (?, ?, ?, ?, ?)";
	
	public static void createDataBase() throws SQLException {
		System.out.println("[INFO] Creating Database ...");
		Connection connection = null;
		Statement statement = null;
		try {
			connection = DriverManager.getConnection(createDBUrl, username, password);
			statement = connection.createStatement();
			statement.executeUpdate(createDatabaseSql);
		} catch (SQLException exception) {
			throw exception;
		} finally {
		    if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
		    if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
		}
	}

	public static void createTables() throws SQLException {
		System.out.println("[INFO] Creating Tables ...");
		Connection connection = null;
		Statement statement = null;
		try {
			connection = DriverManager.getConnection(queryDBUrl, username, password);
			statement = connection.createStatement();
			
			System.out.println("\t--- User");
			statement.executeUpdate(createUserTableSql);
			
			System.out.println("\t--- Train");
			statement.executeUpdate(createTrainTableSql);
			
			System.out.println("\t--- Discount");
			statement.executeUpdate(createDiscountTableSql);
			
			System.out.println("\t--- Seat");
			statement.executeUpdate(createSeatTableSql);
			
			System.out.println("\t--- Order");
			statement.executeUpdate(createOrderTableSql);
			
			System.out.println("\t--- Ticket");
			statement.executeUpdate(createTicketTableSql);
		} catch (SQLException exception) {
			throw exception;
		} finally {
		    if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
		    if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
		}
	}
	
	public static void insertSeats(ArrayList<Seat> seatList) throws SQLException {
		System.out.println("[INFO] Inserting Seats ...");
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DriverManager.getConnection(queryDBUrl, username, password);
			statement = connection.prepareStatement(insertSeatSql);
			for (Seat seat: seatList) {
				statement.setString(1, seat.getSeatID());
				statement.setInt(2, seat.getSeatClass());
				statement.setInt(3, seat.getSeatPreference());
				statement.executeUpdate();
			}
		} catch (SQLException exception) {
			throw exception;
		} finally {
		    if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
		    if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
		}
	}
	
	public static void insertDiscounts(ArrayList<DiscountEntry> discountList) throws SQLException {
		System.out.println("[INFO] Inserting discounts ...");
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DriverManager.getConnection(queryDBUrl, username, password);
			statement = connection.prepareStatement(insertDiscountSql);
			for (DiscountEntry discountEntry: discountList) {
				statement.setInt(1, discountEntry.getTrainID());
				statement.setInt(2, discountEntry.getWeekday());
				statement.setInt(3, discountEntry.getDiscountType());
				statement.setDouble(4, discountEntry.getDiscount());
				statement.setInt(5, discountEntry.getCount());
				statement.executeUpdate();
			}
		} catch (SQLException exception) {
			throw exception;
		} finally {
		    if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
		    if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
		}
	}
}
