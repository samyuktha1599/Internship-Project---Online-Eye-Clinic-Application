package com.g6.onlineeyecare.user.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	
	@Column
	@NotEmpty(message = "cannot be left empty")
	@Size(min = 8, max = 25)
	private String password;
	
	@Column
	@NotEmpty(message = "cannot be left empty")
	@Pattern(regexp = "[A-Z][a-z]+[ ]?[a-zA-Z]+")
	private String userName;
	
	@Column
	@NotEmpty(message = "cannot be left empty")
	private String role;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "UserEntityDTO [userId=" + userId + ", password=" + password + ", userName=" + userName + ", role="
				+ role + "]";
	}
	
	
}
