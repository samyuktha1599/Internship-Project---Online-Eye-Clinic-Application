package com.g6.onlineeyecare.appointment.service;

import java.time.LocalDate;
import java.util.List;

import com.g6.onlineeyecare.appointment.dto.Appointment;
import com.g6.onlineeyecare.exceptions.AppointmentIdNotFoundException;
import com.g6.onlineeyecare.exceptions.DoctorIdNotFoundException;
import com.g6.onlineeyecare.exceptions.InvalidAppointmentException;
import com.g6.onlineeyecare.exceptions.PatientIdFoundNotException;

public interface IAppointmentService {

	Appointment bookAppointment(Appointment appointment) throws DoctorIdNotFoundException, PatientIdFoundNotException;

	Appointment updateAppointment(Appointment appointment) throws InvalidAppointmentException;

	Appointment cancelAppointment(int appointmentId) throws AppointmentIdNotFoundException;

	Appointment viewAppointment(int appointmentId) throws AppointmentIdNotFoundException;

	List<Appointment> viewAllAppointments();

	List<Appointment> viewAppointments(LocalDate date);
	
	List<Appointment> viewAppointmentByDateAndName(LocalDate date,String doctorName);
}
