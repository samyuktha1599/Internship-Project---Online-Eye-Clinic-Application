package com.g6.onlineeyecare.patient.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.g6.onlineeyecare.appointment.dto.Appointment;
import com.g6.onlineeyecare.appointment.service.IAppointmentService;
import com.g6.onlineeyecare.exceptions.AppointmentIdNotFoundException;
import com.g6.onlineeyecare.exceptions.DoctorIdNotFoundException;
import com.g6.onlineeyecare.exceptions.PatientIdFoundNotException;
import com.g6.onlineeyecare.patient.dao.IPatientRepository;
import com.g6.onlineeyecare.patient.dto.Patient;
import com.g6.onlineeyecare.report.dao.IReportRepository;
import com.g6.onlineeyecare.report.dto.Report;

@Service
public class PatientServiceImpl implements IPatientService {

	@Autowired
	IPatientRepository repository;
	@Autowired
	IAppointmentService appointmentService;
	@Autowired
	IReportRepository reportRepository;

	Logger log = LoggerFactory.getLogger(PatientServiceImpl.class);
	
	public PatientServiceImpl(IPatientRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	@Transactional
	public Patient addPatient(Patient patient) {
		try {
			repository.save(patient);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return patient;
	}

	@Override
	@Transactional
	public Patient updatePatient(Patient patient) throws PatientIdFoundNotException {
		
		Optional<Patient> optional = repository.findById(patient.getUserId());
		if (optional.isPresent()) {
			repository.save(patient);
		} else {
			throw new PatientIdFoundNotException("Patient ID not found for updation");
		}
		return optional.get();
	}

	@Override
	@Transactional
	public Patient deletePatient(int patientId) throws PatientIdFoundNotException {
		
		Optional<Patient> optional = repository.findById(patientId);
		if (optional.isPresent()) {
			repository.deleteById(patientId);
		} else {
			throw new PatientIdFoundNotException("Patient id not found for deletion");
		}
		return optional.get();
	}

	@Override
	public List<Patient> viewPatientList() {
		List<Patient> patientList = null;
		try {
			patientList = repository.findAll();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return patientList;
	}

	@Override
	public Patient viewPatient(int patientId) throws PatientIdFoundNotException {
		
		Optional<Patient> optional = repository.findById(patientId);
		if (!optional.isPresent()) {
			throw new PatientIdFoundNotException("Patient id not found to view patient");
		}
		return optional.get();
	}

	@Override
	@Transactional
	public Appointment bookAppointment(Appointment appointment) throws DoctorIdNotFoundException, PatientIdFoundNotException {
		
		return appointmentService.bookAppointment(appointment);
		
	}

	@Override
	public Appointment viewAppointmentDetails(int appointmentid) throws AppointmentIdNotFoundException {
		Appointment appointment = null;
		try {
			appointment = appointmentService.viewAppointment(appointmentid);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new AppointmentIdNotFoundException("Appointment Id not found to view appointment");
		}
		return appointment;
	}

	@Override
	public List<Report> viewReport(int patientId) throws PatientIdFoundNotException {
		List<Report> reports = null;
		reports = reportRepository.findReportByPatientId(patientId);
		if (reports.isEmpty()) {
			throw new PatientIdFoundNotException("Patient Id not found to view the report");
		}
		return reports;
	}

	@Override
	public List<Patient> viewPatientByName(String patientName) {
		List<Patient> list = new ArrayList<>();
		try {
			list = repository.viewByName(patientName);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return list;
	}

}
