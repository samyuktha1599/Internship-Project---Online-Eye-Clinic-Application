package com.g6.onlineeyecare.report.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.g6.onlineeyecare.report.dto.Report;

@Repository
public interface IReportRepository extends JpaRepository<Report, Integer> {

	@Query("Select r from Report r where r.patient.userId = :patientId")
	List<Report> findReportByPatientId(@Param("patientId") Integer patientId);

	@Query("Select r from Report r where r.reportId = :reportId and r.patient.userId = :patientId")
	Report findReportByPatiendIdandReportId(@Param("reportId") Integer reportId, @Param("patientId") Integer patientId);

	@Query("Select r from Report r where r.dateOfReport = :date")
	List<Report> viewReportByDate(@Param("date") LocalDate date);
}
