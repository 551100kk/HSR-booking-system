package controller;

import java.sql.SQLException;

import model.User;

public class AuthController {
	
	public static User login(User user) throws SQLException {
		return DBOperator.selectUser(user);
	}
	
	public static boolean register(User user) {
		try {
			DBOperator.insertUser(user);
			return true;
		} catch (Exception ignore) {}
		return false;
	}
	
	public static void main(String[] str) {
	}
}
