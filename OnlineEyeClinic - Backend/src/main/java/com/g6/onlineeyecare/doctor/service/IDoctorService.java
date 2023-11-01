package com.g6.onlineeyecare.doctor.service;

import java.time.LocalDate;
import java.util.List;

import com.g6.onlineeyecare.appointment.dto.Appointment;
import com.g6.onlineeyecare.doctor.dto.Doctor;
import com.g6.onlineeyecare.exceptions.DoctorIdNotFoundException;
import com.g6.onlineeyecare.exceptions.PatientIdFoundNotException;
import com.g6.onlineeyecare.test.dto.Test;

public interface IDoctorService {
	Doctor addDoctor(Doctor doctor);

	Doctor updateDoctor(Doctor doctor) throws DoctorIdNotFoundException;

	Doctor deleteDoctor(int doctorId) throws DoctorIdNotFoundException;

	Doctor viewDoctor(int doctorId) throws DoctorIdNotFoundException;

	List<Doctor> viewDoctorsList();

	Test createTest(Test test) throws PatientIdFoundNotException;

	List<Appointment> viewAppointments(String doctorName,LocalDate date);
	
	List<Doctor> viewDoctorByName(String doctorName);
}
