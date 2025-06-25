package org.dnyanyog.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.*;

@Entity
@Component
@Table(name = "login") // ✅ Ensure this matches your MySQL table name
public class Login {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
	public Long id;

	@Column(name = "loginName", unique = true, nullable = false)
	public String loginName;

	@Column(name = "password", nullable = false)
	public String password;

	// Constructors
	public Login() {
	}

	public Login(String loginName, String password) {
		this.loginName = loginName;
		this.password = password;
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
