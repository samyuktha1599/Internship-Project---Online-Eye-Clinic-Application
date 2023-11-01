package com.g6.onlineeyecare.test.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.g6.onlineeyecare.patient.dto.Patient;

public class TestDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int testId;
	
	@Column
	@NotEmpty(message = "cannot be left empty")
	@Pattern(regexp = "[A-Z][a-z]+[ ]?[a-zA-Z]+")
	private String testName;
	
	@Column
	@NotEmpty(message = "cannot be left empty")
	@Size(min = 3, max = 20)
	private String testType;
	
	@Column
	@NotEmpty(message = "cannot be left empty")
	private String testDescription;
	
	@Column
	@Min(value = 500, message = "cost cannot be less than 500")
	private double testCost;

	@OneToOne
	@JoinColumn(name = "patient_Id", referencedColumnName = "userId")
	private Patient patient;
	public int getTestId() {
		return testId;
	}
	public void setTestId(int testId) {
		this.testId = testId;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getTestType() {
		return testType;
	}
	public void setTestType(String testType) {
		this.testType = testType;
	}
	public String getTestDescription() {
		return testDescription;
	}
	public void setTestDescription(String testDescription) {
		this.testDescription = testDescription;
	}
	public double getTestCost() {
		return testCost;
	}
	public void setTestCost(double testCost) {
		this.testCost = testCost;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	@Override
	public String toString() {
		return "TestDTO [testId=" + testId + ", testName=" + testName + ", testType=" + testType + ", testDescription="
				+ testDescription + ", testCost=" + testCost + ", patient=" + patient + "]";
	}
	
	
}
