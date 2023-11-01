package com.g6.onlineeyecare.test.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.g6.onlineeyecare.patient.dto.Patient;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Test Entity")
@Entity
@Table(name = "eye_test")
public class Test {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int testId;
	@ApiModelProperty(name = "test name", required = true)
	@Column
	@NotEmpty(message = "cannot be left empty")
	@Pattern(regexp = "[A-Z][a-z]+[ ]?[a-zA-Z]+")
	private String testName;
	@ApiModelProperty(name = "test type", required = true)
	@Column
	@NotEmpty(message = "cannot be left empty")
	@Size(min = 3, max = 20)
	private String testType;
	@ApiModelProperty(name = "test discription", required = true)
	@Column
	@NotEmpty(message = "cannot be left empty")
	private String testDescription;
	@ApiModelProperty(name = "test cost", required = true)
	@Column
	@Min(value = 500, message = "cost cannot be less than 500")
	private double testCost;

	@OneToOne
	@JoinColumn(name = "patient_Id", referencedColumnName = "userId")
	private Patient patient;

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

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

	public Test() {
		super();
		
	}

	public Test(int testId, String testName, String testType, String testDescription, double testCost,
			Patient patient) {
		super();
		this.testId = testId;
		this.testName = testName;
		this.testType = testType;
		this.testDescription = testDescription;
		this.testCost = testCost;
		this.patient = patient;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + testId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Test other = (Test) obj;
		if (testId != other.testId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Test [testId=" + testId + ", testName=" + testName + ", testType=" + testType + ", testDescription="
				+ testDescription + ", testCost=" + testCost + ", patient=" + patient + "]";
	}

}
