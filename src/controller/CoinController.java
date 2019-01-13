package controller;

import model.User;

public class CoinController {
	public static void updateCoin(User user, long increaseAmount) throws Exception {
		DBOperator.updateUserHsrcoin(user, increaseAmount);
	}
}
