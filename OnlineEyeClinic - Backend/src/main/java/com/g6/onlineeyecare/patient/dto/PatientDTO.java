package com.g6.onlineeyecare.patient.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.g6.onlineeyecare.user.dto.UserDTO;

import io.swagger.annotations.ApiModelProperty;


public class PatientDTO extends UserDTO{

	
	@Column
	@Min(value = 1)
	private int patientAge;
	
	
	@ApiModelProperty(name = "Mobile", value = "Mobile number cannot be null, holds max and min 10 digits")
	@Column
	@NotEmpty(message = "Mobile number cannot be left blank or null")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Enter 10 digit mobile number")
	private String patientMobile;
	
	
	@Column
	@Email(message = "Email should be valid")
	@NotEmpty(message = "Email cannot be empty")
	private String patientEmail;
	
	@Column
	private LocalDate patientDOB;
	
	@Column
	@Size(max = 512, message = "Patient address cannot be more than 512 characters")
	private String address;
	public int getPatientAge() {
		return patientAge;
	}
	public void setPatientAge(int patientAge) {
		this.patientAge = patientAge;
	}
	
	
	public String getPatientMobile() {
		return patientMobile;
	}
	public void setPatientMobile(String patientMobile) {
		this.patientMobile = patientMobile;
	}
	public String getPatientEmail() {
		return patientEmail;
	}
	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}
	public LocalDate getPatientDOB() {
		return patientDOB;
	}
	public void setPatientDOB(LocalDate patientDOB) {
		this.patientDOB = patientDOB;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "PatientDTO [patientAge=" + patientAge + ", patientMobile=" + patientMobile + ", patientEmail="
				+ patientEmail + ", patientDOB=" + patientDOB + ", address=" + address + "]";
	}
	
	
}
