package com.g6.onlineeyecare.patient.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.g6.onlineeyecare.patient.dto.Patient;

@Repository
public interface IPatientRepository extends JpaRepository<Patient, Integer>{
	
	@Query("Select a from Patient a where a.userName=:patientName")
	List<Patient> viewByName(@Param("patientName") String patientName);
}
