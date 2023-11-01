package com.g6.onlineeyecare.appointment.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.g6.onlineeyecare.appointment.dto.Appointment;

@Repository
public interface IAppointmentRepository extends JpaRepository<Appointment, Integer>{
	
	@Query("Select a from Appointment a where a.appointmentDate=:date")
    List<Appointment> viewAppointmentByDate(@Param("date") LocalDate date);
	
	@Query("Select a from Appointment a where a.appointmentDate=:date and a.doctor.userName=:doctorName ORDER BY appointmentTime")
    List<Appointment> viewAppointmentByName(@Param("date") LocalDate date,@Param("doctorName") String doctorName);
}
