package com.g6.onlineeyecare.report.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import com.g6.onlineeyecare.patient.dto.Patient;
import com.g6.onlineeyecare.test.dto.Test;

public class ReportDTO {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int reportId;

	
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfReport;
	
	@Column
	@NotEmpty(message = "cannot be left empty")
	private String descriptionOfReport;
	
	@Column
	@NotEmpty(message = "cannot be left empty")
	private String visualAcuity;
	
	@Column
	@NotEmpty(message = "cannot be left empty")
	private String visualAcuityNear;
	
	@Column
	@NotEmpty(message = "cannot be left empty")
	private String visualAcuityDistance;

	@OneToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "test_Id", referencedColumnName = "testId")
	private Test typeOfTest;

	@OneToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "patient_Id", referencedColumnName = "userId")
	private Patient patient;
	public int getReportId() {
		return reportId;
	}
	public void setReportId(int reportId) {
		this.reportId = reportId;
	}
	public LocalDate getDateOfReport() {
		return dateOfReport;
	}
	public void setDateOfReport(LocalDate dateOfReport) {
		this.dateOfReport = dateOfReport;
	}
	public String getDescriptionOfReport() {
		return descriptionOfReport;
	}
	public void setDescriptionOfReport(String descriptionOfReport) {
		this.descriptionOfReport = descriptionOfReport;
	}
	public String getVisualAcuity() {
		return visualAcuity;
	}
	public void setVisualAcuity(String visualAcuity) {
		this.visualAcuity = visualAcuity;
	}
	public String getVisualAcuityNear() {
		return visualAcuityNear;
	}
	public void setVisualAcuityNear(String visualAcuityNear) {
		this.visualAcuityNear = visualAcuityNear;
	}
	public String getVisualAcuityDistance() {
		return visualAcuityDistance;
	}
	public void setVisualAcuityDistance(String visualAcuityDistance) {
		this.visualAcuityDistance = visualAcuityDistance;
	}
	public Test getTypeOfTest() {
		return typeOfTest;
	}
	public void setTypeOfTest(Test typeOfTest) {
		this.typeOfTest = typeOfTest;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	@Override
	public String toString() {
		return "ReportDTO [reportId=" + reportId + ", dateOfReport=" + dateOfReport + ", descriptionOfReport="
				+ descriptionOfReport + ", visualAcuity=" + visualAcuity + ", visualAcuityNear=" + visualAcuityNear
				+ ", visualAcuityDistance=" + visualAcuityDistance + ", typeOfTest=" + typeOfTest + ", patient="
				+ patient + "]";
	}
	
	
}
