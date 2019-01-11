package controller;

import java.sql.*;

import model.*;

public class DBOperator {
	// connection information
	static final String dbName = "HighSpeedRail";
	static final String connectionArguments = "?useSSL=false&serverTimezone=UTC";
	static final String queryDBUrl = "jdbc:mysql://127.0.0.1:3306/" + dbName + connectionArguments;
	static final String username = "root";
	static final String password = "root";
	
	// SQL statement
	static final String selectUserSql = "SELECT * FROM User Where username=? and password=?";
	static final String insertUserSql = "INSERT INTO User VALUES (?, ?, ?, ?)";
	
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
}
