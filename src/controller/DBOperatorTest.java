package controller;
import model.User;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

@Nested
@DisplayName("DBOperator testing")
class DBOperatorTest {
	User usr;
	@BeforeEach
	void userassign() {
		String username = "aisaiudhs";
		usr = new User(username, "kiji", "sjij@sji.com", "0909282828", 0);
	}
	
	@Test
	@DisplayName("updateUserHsrcoin test")
	void updateUserHsrcoinTest() {
		try {
			DBOperator.updateUserHsrcoin(usr, 1000);
		} catch (Exception e) {
			fail("SQL ERROR");
		}
	}
	
	@Test
	@DisplayName("selectTicketsByUser")
	void selectTicketsByUserTest() {
		try {
			DBOperator.selectTicketsByUser(usr);
		} catch (Exception e) {
			fail("SQL ERROR");
		}
	}

}
