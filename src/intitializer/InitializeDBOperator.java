package intitializer;

import java.sql.*;
import java.util.ArrayList;

import model.Seat;

public class InitializeDBOperator {
	// connection information
	static final String dbName = "HighSpeedRail";
	static final String connectionArguments = "?useSSL=false&serverTimezone=UTC";
	static final String createDBUrl = "jdbc:mysql://127.0.0.1:3306/" + connectionArguments;
	static final String queryDBUrl = "jdbc:mysql://127.0.0.1:3306/" + dbName + connectionArguments;
	static final String username = "root";
	static final String password = "root";
	
	// SQL statement
	static final String createDatabaseSql = "CREATE DATABASE IF NOT EXISTS " + dbName;
	static final String createUserTableSql = "CREATE TABLE IF NOT EXISTS User (\n" + 
			"    username varchar(64) NOT NULL PRIMARY KEY,\n" + 
			"    password varchar(64) NOT NULL,\n" + 
			"    email varchar(64),\n" + 
			"    phoneNumber int\n" + 
			"); ";
	static final String createTrainTableSql = "CREATE TABLE IF NOT EXISTS Train (\n" + 
			"    trainID int NOT NULL,\n" + 
			"    date int NOT NULL,\n" + 
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
			"    PRIMARY KEY (trainID, date)\n" + 
			");";
	static final String createDiscountTableSql = "CREATE TABLE IF NOT EXISTS Discount (\n" + 
			"    trainID int NOT NULL,\n" + 
			"    date int NOT NULL,\n" + 
			"    discountType int NOT NULL,\n" + 
			"    discount float,\n" + 
			"    count int,\n" + 
			"    FOREIGN KEY (trainID, date) REFERENCES Train(trainID, date),\n" + 
			"    PRIMARY KEY (discountType, discount)\n" + 
			");";
	static final String createSeatTableSql = "CREATE TABLE IF NOT EXISTS Seat (\n" + 
			"    seatID varchar(8) NOT NULL,\n" + 
			"    seatClass int NOT NULL,\n" + 
			"    seatPreference int NOT NULL,\n" + 
			"    PRIMARY KEY (seatID)\n" + 
			");";
	static final String createOrderTableSql = "CREATE TABLE IF NOT EXISTS UserOrder (\n" + 
			"    orderID int NOT NULL PRIMARY KEY,\n" + 
			"    username varchar(64) NOT NULL,\n" + 
			"    createTime datetime NOT NULL,\n" + 
			"    FOREIGN KEY (username) REFERENCES User(username)\n" + 
			");";
	static final String createTicketTableSql = "CREATE TABLE IF NOT EXISTS Ticket (\n" + 
			"    ticketID int NOT NULL PRIMARY KEY,\n" + 
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
			"    FOREIGN KEY (trainID, date) REFERENCES Train(trainID, date),\n" + 
			"    FOREIGN KEY (discountType, discount) REFERENCES Discount(discountType, discount)\n" + 
			");";
	static final String insertSeatSql = "INSERT INTO Seat (seatID, seatClass, seatPreference)\n" + 
			"VALUES (?, ?, ?)";
	
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
	
	public static void InsertSeats(ArrayList<Seat> seatList) throws SQLException {
		System.out.println("[INFO] Inserting Seats ...");
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DriverManager.getConnection(queryDBUrl, username, password);
			statement = connection.prepareStatement(insertSeatSql);
			for (Seat seat: seatList) {
				statement.setString(1, seat.seatID);
				statement.setInt(2, seat.seatClass);
				statement.setInt(3, seat.seatPreference);
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
