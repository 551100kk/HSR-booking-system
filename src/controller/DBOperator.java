package controller;

import java.sql.*;
import java.util.ArrayList;

import model.*;
import model.Date;
import model.Time;

public class DBOperator {
	// connection information
	private static final String dbName = "HighSpeedRail";
	private static final String connectionArguments = "?useSSL=false&serverTimezone=UTC";
	private static final String queryDBUrl = "jdbc:mysql://127.0.0.1:3306/" + dbName + connectionArguments;
	private static final String username = "root";
	private static final String password = "root";
	
	// SQL statement
	private static final String selectUserSql = "SELECT * FROM User WHERE username=? and password=?";
	private static final String insertUserSql = "INSERT INTO User VALUES (?, ?, ?, ?)";
	private static final String selectTimeTableEntrySql = "SELECT * FROM TimeTable WHERE date=?";
	private static final String insertTimeTableEntrySql = "INSERT INTO TimeTable "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String selectTicketsByUserSql = "SELECT * FROM Ticket NATURAL JOIN Seat WHERE username=?";
	private static final String selectTicketsByTicketIDSql = "SELECT * FROM Ticket NATURAL JOIN Seat WHERE ticketID=?";
	private static final String insertTicketSql = "INSERT INTO Ticket "
			+ "(username,orderID,seatID,trainID,date,fromStation,toStation,departureTime,arrivalTime,discountType,discount) "
			+ "VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String deleteTicketByIDSql = "DELETE FROM Ticket WHERE TicketID=?";
	private static final String insertOrderSql = "INSERT INTO UserOrder (username,createTime) VALUES ( ?, NOW())";
	private static final String selectOrderByIDSql = "SELECT * FROM UserOrder NATURAL JOIN Ticket NATURAL JOIN Seat WHERE orderID=?";
	private static final String selectOrderByUserSql = "SELECT * FROM UserOrder WHERE username=? order by orderID desc";
	private static final String deleteOrderByIDSql = "DELETE FROM UserOrder WHERE orderID=?";
	private static final String selectAvailableSeatSql = "SELECT * FROM Seat WHERE seatID not in (select seatid from ticket where trainID=? and date=?) AND seatClass=? AND seatPreference like ?";
	// query
	synchronized public static boolean selectUser(User user) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = DriverManager.getConnection(queryDBUrl, username, password);
			statement = connection.prepareStatement(selectUserSql);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			result = statement.executeQuery();
			if (result.next())
				return true;
		} catch (SQLException exception) {
			throw exception;
		} finally {
		    if (result != null) try { result.close(); } catch (SQLException ignore) {}
		    if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
		    if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
		}
		return false;
	}
	
	synchronized public static void insertUser(User user) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DriverManager.getConnection(queryDBUrl, username, password);
			statement = connection.prepareStatement(insertUserSql);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getPhoneNumber());
			statement.executeUpdate();
		} catch (SQLException exception) {
			throw exception;
		} finally {
		    if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
		    if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
		}
	}
	
	synchronized public static ArrayList<TimeTableEntry> selectTimeTableEntry(Date date) throws Exception {
		ArrayList<TimeTableEntry> timeTableList = new ArrayList<TimeTableEntry>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = DriverManager.getConnection(queryDBUrl, username, password);
			statement = connection.prepareStatement(selectTimeTableEntrySql);
			statement.setString(1, date.toString());
			result = statement.executeQuery();
			while (result.next()) {
				TimeTableEntry timeTableEntry = new TimeTableEntry();
				timeTableEntry.setTrainID(result.getInt("trainID"));
				timeTableEntry.setDate(new Date(result.getString("date")));
				timeTableEntry.setDirection(result.getInt("direction"));
				for (String station: Constant.stationEnglishName) {
					timeTableEntry.getTimeList().add(new Time(result.getString(station)));
				}
				timeTableList.add(timeTableEntry);
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
		    if (result != null) try { result.close(); } catch (SQLException ignore) {}
		    if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
		    if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
		}
		return timeTableList;
	}
	
	synchronized public static void insertTimeTableEntry(TimeTableEntry timeTableEntry) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DriverManager.getConnection(queryDBUrl, username, password);
			statement = connection.prepareStatement(insertTimeTableEntrySql);
			statement.setInt(1, timeTableEntry.getTrainID());
			statement.setString(2, timeTableEntry.getDate().toString());
			statement.setInt(3, timeTableEntry.getDate().getDayOfWeek());
			statement.setInt(4, timeTableEntry.getDirection());
			int index = 5;
			for (Time time: timeTableEntry.getTimeList()) {
				statement.setString(index++, time.toString());
			}
			statement.executeUpdate();
		} catch (Exception exception) {
			throw exception;
		} finally {
		    if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
		    if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
		}
	}

	synchronized public static ArrayList<Ticket> selectTicketsByUser(User user) throws Exception {
		ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = DriverManager.getConnection(queryDBUrl, username, password);
			statement = connection.prepareStatement(selectTicketsByUserSql);
			statement.setString(1, user.getUsername());
			result = statement.executeQuery();
			while (result.next()) {
				Ticket ticket = new Ticket();
				ticket.setTicketID(result.getInt("ticketID"));
				ticket.setUsername(result.getString("username"));
				ticket.setTrainID(result.getInt("trainID"));
				ticket.setDate(new Date(result.getString("date")));
				ticket.setSeatID(result.getString("seatID"));
				ticket.setSeatClass(result.getInt("seatClass"));
				ticket.setFromStation(result.getInt("FromStation"));
				ticket.setToStation(result.getInt("toStation"));
				ticket.setDepartureTime(new Time(result.getString("departureTime")));
				ticket.setArrivalTime(new Time(result.getString("arrivalTime")));
				ticket.setDiscount(result.getFloat("discount"));
				ticket.setDiscountType(result.getInt("discountType"));
				ticketList.add(ticket);
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
		    if (result != null) try { result.close(); } catch (SQLException ignore) {}
		    if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
		    if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
		}
		return ticketList;
	}

	synchronized public static Ticket selectTicketByID(int TicketID) throws Exception {
		Ticket ticket = new Ticket();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = DriverManager.getConnection(queryDBUrl, username, password);
			statement = connection.prepareStatement(selectTicketsByTicketIDSql);
			statement.setInt(1, TicketID);
			result = statement.executeQuery();
			while (result.next()) {
				ticket.setTicketID(result.getInt("ticketID"));
				ticket.setUsername(result.getString("username"));
				ticket.setTrainID(result.getInt("trainID"));
				ticket.setDate(new Date(result.getString("date")));
				ticket.setSeatID(result.getString("seatID"));
				ticket.setSeatClass(result.getInt("seatClass"));
				ticket.setFromStation(result.getInt("FromStation"));
				ticket.setToStation(result.getInt("toStation"));
				ticket.setDepartureTime(new Time(result.getString("departureTime")));
				ticket.setArrivalTime(new Time(result.getString("arrivalTime")));
				ticket.setDiscount(result.getFloat("discount"));
				ticket.setDiscountType(result.getInt("discountType"));
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
		    if (result != null) try { result.close(); } catch (SQLException ignore) {}
		    if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
		    if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
		}
		return ticket;
	}
	
	synchronized public static void insertTickets(ArrayList<Ticket> ticketList, int OrderID) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DriverManager.getConnection(queryDBUrl, username, password);
			statement = connection.prepareStatement(insertTicketSql);
			connection.setAutoCommit(false);
			for (Ticket ticket : ticketList) {
				statement.setString(1, ticket.getUsername());
				statement.setInt(2, OrderID);
				statement.setString(3, ticket.getSeatID());
				statement.setInt(4, ticket.getTrainID());
				statement.setString(5, ticket.getDate().toString());
				statement.setInt(6, ticket.getFromStation());
				statement.setInt(7, ticket.getToStation());
				statement.setString(8, ticket.getDepartureTime().toString());
				statement.setString(9, ticket.getArrivalTime().toString());
				statement.setInt(10, ticket.getDiscountType());
				statement.setDouble(11, ticket.getDiscount());
				statement.executeUpdate();
			}
			connection.commit();
		} catch (SQLException exception) {
			exception.printStackTrace();
			connection.rollback();
			throw exception;
		} finally {
		    if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
		    if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
		}
	}
	
	synchronized public static void deleteTicketByID(int TicketID) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DriverManager.getConnection(queryDBUrl, username, password);
			statement = connection.prepareStatement(deleteTicketByIDSql);
			statement.setInt(1, TicketID);
			statement.executeUpdate();
		} catch (SQLException exception) {
			throw exception;
		} finally {
		    if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
		    if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
		}
	}
	
	synchronized public static ArrayList<Train> selectTrain(BookCondition bookCondition, boolean isInbound) throws Exception {
		ArrayList<Train> trainList = new ArrayList<Train>();
		Connection connection = null;
		String fromStation = Constant.stationEnglishName[bookCondition.getFromStation()];
		String toStation = Constant.stationEnglishName[bookCondition.getToStation()];
		String sql = "select final.*, IFNULL(allDiscount.discount, 1.0) as discount, (IFNULL(allDiscount.count, 0) - IFNULL(bookedDiscount, 0)) as availDiscount from ("
				+ "select timeTable.*, (allSeat - IFNULL(booked, 0)) as avail from "
				/* Select train */
				+ "(select * from timeTable where trainID like ? and date=? and " + fromStation + ">=? and " 
				+ toStation + ">=0 and direction=?) as timeTable "
				+  "left join ("
				/* Count  booked tickets*/
				+ "select trainID, count(*) as booked from Ticket "
				+ "natural join seat where seatClass=? and seatPreference like ? and date=? and" 
				+ "(toStation" + (bookCondition.getDirection() == 0 ? ">" : "<") + bookCondition.getFromStation() + " or "
				+ "fromStation" + (bookCondition.getDirection() == 0 ? "<" : ">") + bookCondition.getToStation()
				+ ") group by trainID"
				+ ") as s on timeTable.trainID=s.trainID "
				/* Count all seats */
				+ "join (select count(*) as allSeat from seat where seatClass=? and seatPreference like ?) as allSeat"
				+ ") as final "
				+ "left join "
				/* Count booked discount*/
				+ "(select trainID, count(*) as bookedDiscount from Ticket where discountType=? and date=? and"
				+ "(toStation" + (bookCondition.getDirection() == 0 ? ">" : "<") + bookCondition.getFromStation() + " or "
				+ "fromStation" + (bookCondition.getDirection() == 0 ? "<" : ">") + bookCondition.getToStation()
				+ ") group by trainID) "
				+ "as d on final.trainID=d.trainID "
				/* Count all discount*/
				+ "left join"
				+ "(select * from discount where discountType=?) as allDiscount "
				+ "on allDiscount.trainID=final.trainID and allDiscount.dayOfWeek=final.dayOfWeek "
				+ "where avail>=? and ((IFNULL(allDiscount.count, 0) - IFNULL(bookedDiscount, 0))>=? or ?=0)"
				+ "order by " + fromStation;

		String date = (isInbound ? bookCondition.getDateIn() : bookCondition.getDateOut()).toString();
		String trainID = bookCondition.getSearchType() == 0 ?  "%" :
			Integer.toString(isInbound ? bookCondition.getTrainIDIn() : bookCondition.getTrainIDOut());
		String departureTime = (bookCondition.getSearchType() == 0 ? 
				(isInbound ? bookCondition.getDepartureTimeIn() : bookCondition.getDepartureTimeOut()).toString() : 
				"0000");
		String seatPreference = bookCondition.getSeatPreference() == 0 ? 
				"%" : Integer.toString(bookCondition.getSeatPreference());
		int seatClass = bookCondition.getSeatClass() == 1 ? 1 : 0;
		int discountType = (bookCondition.isEarlyBird() ? 2 : (bookCondition.getSeatClass() == 2) ? 1 : 0);

		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = DriverManager.getConnection(queryDBUrl, username, password);
			statement = connection.prepareStatement(sql);
			statement.setString(1, trainID);
			statement.setString(2, date);
			statement.setString(3, departureTime);
			statement.setInt(4, bookCondition.getDirection());
			statement.setInt(5, seatClass);
			statement.setString(6, seatPreference);
			statement.setString(7, date);
			statement.setInt(8, seatClass);
			statement.setString(9, seatPreference);
			statement.setInt(10, discountType);
			statement.setString(11, date);
			statement.setInt(12, discountType);
			statement.setInt(13, bookCondition.getPassengers());
			statement.setInt(14, bookCondition.getPassengers());
			statement.setInt(15, discountType);
			result = statement.executeQuery();
			while (result.next()) {
				Train train = new Train();
				train.setTrainID(result.getInt("trainID"));
				train.setDiscount(result.getDouble("discount"));
				train.setDepartureTime(new Time(result.getString(fromStation)));
				train.setArrivalTime(new Time(result.getString(toStation)));
				trainList.add(train);
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
		    if (result != null) try { result.close(); } catch (SQLException ignore) {}
		    if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
		    if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
		}
		return trainList;
	}

	synchronized public static void insertOrder(Order order) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DriverManager.getConnection(queryDBUrl, username, password);
			statement = connection.prepareStatement(insertOrderSql);
			statement.setString(1, order.getUsername());
			statement.executeUpdate();
		} catch (SQLException exception) {
			throw exception;
		} finally {
		    if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
		    if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
		}
	}

	synchronized public static Order selectOrderByID(int orderID) throws Exception {
		Order order = new Order();
		ArrayList<Ticket> ticketList = new ArrayList<Ticket>();
		order.setTickets(ticketList);
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = DriverManager.getConnection(queryDBUrl, username, password);
			statement = connection.prepareStatement(selectOrderByIDSql);
			statement.setInt(1, orderID);
			result = statement.executeQuery();
			while (result.next()) {
				Ticket ticket = new Ticket();
				ticket.setTicketID(result.getInt("ticketID"));
				ticket.setUsername(result.getString("username"));
				ticket.setTrainID(result.getInt("trainID"));
				ticket.setDate(new Date(result.getString("date")));
				ticket.setSeatID(result.getString("seatID"));
				ticket.setSeatClass(result.getInt("seatClass"));
				ticket.setFromStation(result.getInt("FromStation"));
				ticket.setToStation(result.getInt("toStation"));
				ticket.setDepartureTime(new Time(result.getString("departureTime")));
				ticket.setArrivalTime(new Time(result.getString("arrivalTime")));
				ticket.setDiscount(result.getFloat("discount"));
				ticket.setDiscountType(result.getInt("discountType"));
				ticketList.add(ticket);
				order.setOrderID(result.getInt("orderID"));
				order.setUsername(result.getString("username"));
				order.setCreateTime(result.getDate("createTime"));
			}
		} catch (SQLException exception) {
			throw exception;
		} finally {
		    if (result != null) try { result.close(); } catch (SQLException ignore) {}
		    if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
		    if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
		}
		return order;
	}
	
	synchronized public static ArrayList<Order> selectOrderByUser(User user) throws Exception {
		ArrayList<Order> orderList = new ArrayList<Order>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = DriverManager.getConnection(queryDBUrl, username, password);
			statement = connection.prepareStatement(selectOrderByUserSql);
			statement.setString(1, user.getUsername());
			result = statement.executeQuery();
			while (result.next()) {
				int orderID = result.getInt("orderID");
				Order order = selectOrderByID(result.getInt("orderID"));
				if (order.getOrderID() == 0) {
					order.setOrderID(orderID);
				}
				orderList.add(order);
			}
		} catch (SQLException exception) {
			throw exception;
		} finally {
		    if (result != null) try { result.close(); } catch (SQLException ignore) {}
		    if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
		    if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
		}
		return orderList;
	}

	synchronized public static void deleteOrderByID(int orderID) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DriverManager.getConnection(queryDBUrl, username, password);
			statement = connection.prepareStatement(deleteOrderByIDSql);
			statement.setInt(1, orderID);
			statement.executeUpdate();
		} catch (SQLException exception) {
			throw exception;
		} finally {
		    if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
		    if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
		}
	}

	synchronized public static ArrayList<Seat> selectAvailableSeats(Train train, BookCondition bookcondition, boolean isInbound) throws Exception {	
		ArrayList<Seat> seatList = new ArrayList<Seat>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = DriverManager.getConnection(queryDBUrl, username, password);
			statement = connection.prepareStatement(selectAvailableSeatSql);
			statement.setInt(1, train.getTrainID());
			if(isInbound)
				statement.setString(2, bookcondition.getDateIn().toString());
			else
				statement.setString(2, bookcondition.getDateOut().toString());
			statement.setInt(3, bookcondition.getSeatClass() == 1 ? 1 : 0);
			statement.setString(4, bookcondition.getSeatPreference() == 0 ? "%" : Integer.toString(bookcondition.getSeatPreference()));
			result = statement.executeQuery();
			while (result.next()) {
				Seat seat = new Seat(result.getString("seatID"),result.getInt("seatClass"),result.getInt("seatPreference"));
				seatList.add(seat);
			}
		} catch (SQLException exception) {
			throw exception;
		} finally {
		    if (result != null) try { result.close(); } catch (SQLException ignore) {}
		    if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
		    if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
		}
		return seatList;
	}	
}

