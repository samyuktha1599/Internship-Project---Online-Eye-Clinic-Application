package com.g6.onlineeyecare.patient.service;

import java.util.List;

import com.g6.onlineeyecare.appointment.dto.Appointment;
import com.g6.onlineeyecare.exceptions.AppointmentIdNotFoundException;
import com.g6.onlineeyecare.exceptions.DoctorIdNotFoundException;
import com.g6.onlineeyecare.exceptions.PatientIdFoundNotException;
import com.g6.onlineeyecare.patient.dto.Patient;
import com.g6.onlineeyecare.report.dto.Report;

public interface IPatientService {
	Patient addPatient(Patient patient);

	Patient updatePatient(Patient patient) throws PatientIdFoundNotException;

	Patient deletePatient(int patientId) throws PatientIdFoundNotException;

	List<Patient> viewPatientList();

	Patient viewPatient(int patientId) throws PatientIdFoundNotException;

	Appointment bookAppointment(Appointment appointment) throws DoctorIdNotFoundException, PatientIdFoundNotException;

	Appointment viewAppointmentDetails(int appointmentid) throws AppointmentIdNotFoundException;

	List<Report> viewReport(int patientId) throws PatientIdFoundNotException;
	
	List<Patient> viewPatientByName(String patientName);
}
