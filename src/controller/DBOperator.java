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
	
	// query
	public static boolean selectUser(User user) throws SQLException {
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
	
	public static void insertUser(User user) throws SQLException {
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
	
	public static ArrayList<TimeTableEntry> selectTimeTableEntry(Date date) throws Exception {
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
	
	public static void insertTimeTableEntry(TimeTableEntry timeTableEntry) throws Exception {
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
}
