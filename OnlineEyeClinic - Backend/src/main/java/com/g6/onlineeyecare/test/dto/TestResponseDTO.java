package com.g6.onlineeyecare.test.dto;

public class TestResponseDTO {

	private int testId;
	private String testName;
	private String testType;
	private String testDescription;
	private double testCost;
	private int patientId;
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
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	@Override
	public String toString() {
		return "TestResponseDTO [testId=" + testId + ", testName=" + testName + ", testType=" + testType
				+ ", testDescription=" + testDescription + ", testCost=" + testCost + ", patientId=" + patientId + "]";
	}
	
	
	
}
