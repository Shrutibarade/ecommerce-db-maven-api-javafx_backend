package org.dnyanyog.dto;

public class LoginRequest {
	public String loginName;
	public String password;

	// Constructor (Optional)
	public LoginRequest() {
	}

	public LoginRequest(String loginName, String password) {
		this.loginName = loginName;
		this.password = password;
	}

	// Getters & Setters
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
