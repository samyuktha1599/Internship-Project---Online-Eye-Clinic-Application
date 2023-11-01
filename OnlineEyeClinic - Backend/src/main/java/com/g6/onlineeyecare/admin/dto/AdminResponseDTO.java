package com.g6.onlineeyecare.admin.dto;

public class AdminResponseDTO {

	private int userId;
	private String adminName;
	private String email;
	private String mobile;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Override
	public String toString() {
		return "AdminResponseDTO [userId=" + userId + ", adminName=" + adminName + ", email=" + email + ", mobile="
				+ mobile + "]";
	}
	
	
}
