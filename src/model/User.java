package model;

public class User {
	private String username;
	private String password;
	private String email;
	private String phoneNumber;
	
	public User(String username, String password, String email, String phoneNumber) {
		super();
		this.setUsername(username);
		this.setPassword(password);
		this.setEmail(email);
		this.setPhoneNumber(phoneNumber);
	}

	public User(String username, String password) {
		super();
		this.setUsername(username);
		this.setPassword(password);
	}
	
	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
