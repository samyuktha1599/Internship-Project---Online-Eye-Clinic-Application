package com.g6.onlineeyecare.doctor.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.g6.onlineeyecare.doctor.dto.Doctor;

@Repository
public interface IDoctorRepository extends JpaRepository<Doctor, Integer> {

	@Query("Select a from Doctor a where a.userName=:doctorName")
	List<Doctor> viewDoctorByName(@Param("doctorName") String doctorName);
}
