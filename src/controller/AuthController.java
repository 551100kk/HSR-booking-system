package controller;

import model.User;

public class AuthController {
	
	public static boolean login(User user) {
		try {
			if (DBOperator.selectUser(user))
				return true;
		} catch (Exception ignore) {}
		return false;
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
