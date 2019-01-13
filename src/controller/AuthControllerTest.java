package controller;
import model.User;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

@Nested
@DisplayName("AuthController testing")
class AuthControllerTest {
	User us, usr;
	
	@BeforeEach
	void userassign() {
		String username = "aisaiudhs";
		usr = new User(username, "kiji", "sjij@sji.com", "0909282828", 0);
		us = new User(username, "kiji");
	}
	
	@Test
	@DisplayName("Failed login test")
	void a_loginTest_f() {
		try {
			assertNull(AuthController.login(us));
		} catch (SQLException e) {
			fail("SQL ERROR");
		}
	}
	
	@Test
	@DisplayName("Success register test")
	void b_registerTest_s() {
		assertTrue(AuthController.register(usr));
	}
	
	
	@Test
	@DisplayName("Fail register test")
	void c_registerTest_f() {
		assertFalse(AuthController.register(usr));
	}
	
	@Test
	@DisplayName("Success login test")
	void d_loginTest_s() {
		try {
			assertNotNull(AuthController.login(us));
		} catch (SQLException e) {
			fail("SQL ERROR");
		}
	}

}
