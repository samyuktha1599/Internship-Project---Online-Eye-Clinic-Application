package com.g6.onlineeyecare.patientservicetest;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import org.springframework.boot.test.context.SpringBootTest;

import com.g6.onlineeyecare.appointment.dao.IAppointmentRepository;
import com.g6.onlineeyecare.appointment.service.AppointmentServiceImpl;
import com.g6.onlineeyecare.appointment.service.IAppointmentService;
import com.g6.onlineeyecare.exceptions.PatientIdFoundNotException;
import com.g6.onlineeyecare.patient.dao.IPatientRepository;
import com.g6.onlineeyecare.patient.dto.Patient;
import com.g6.onlineeyecare.patient.service.PatientServiceImpl;
import com.g6.onlineeyecare.report.dao.IReportRepository;
import com.g6.onlineeyecare.report.service.IReportService;
import com.g6.onlineeyecare.report.service.ReportServiceImpl;

@SpringBootTest
public class PatientServiceTest {

	IPatientRepository repository;
	IAppointmentRepository appointmentRepository;
	IReportRepository reportRepository;

	@InjectMocks
	static PatientServiceImpl patientService;
	static IAppointmentService appointmentService;
	static IReportService reportService;
	private static AutoCloseable ac;

	@Before
	public void doinit() {
		repository = mock(IPatientRepository.class);
		appointmentRepository = mock(IAppointmentRepository.class);
		reportRepository = mock(IReportRepository.class);

		patientService = new PatientServiceImpl(repository);
		appointmentService = new AppointmentServiceImpl(appointmentRepository);
		reportService = new ReportServiceImpl(reportRepository);

		ac = MockitoAnnotations.openMocks(this);
	}

	@AfterEach
	public void doAtEnd() throws Exception {
		ac.close();
	}

	@Test
	@DisplayName("test -> view patient by Id")
	public void testViewPatientById() throws PatientIdFoundNotException {
		Patient p1 = new Patient(20, "805063752", "abc@gmail.com", LocalDate.now(), "bangalore");
		p1.setUserId(2);

		when(repository.findById(2)).thenReturn(Optional.of(p1));
		Patient p2 = patientService.viewPatient(2);
		assertEquals(p1, p2);
		verify(repository).findById(2);
	}

	@Test
	@DisplayName("test -> view patient by Id with invalid entries")
	public void testViewPatientByIdInvalid() throws PatientIdFoundNotException {
		Patient p1 = new Patient(20, "805063752", "abc@gmail.com", LocalDate.now(), "bangalore");
		p1.setUserId(2);

		when(repository.findById(2)).thenReturn(Optional.of(p1));
		Executable executable = () -> patientService.viewPatient(3);
		assertThrows(PatientIdFoundNotException.class, executable);
	}

	@Test
	@DisplayName("test -> view all patients")
	public void ViewAllPatient() {
		Patient p1 = new Patient(20, "805063752", "abc@gmail.com", LocalDate.now(), "bangalore");
		Patient p2 = new Patient(25, "805063735", "xys@gmail.com", LocalDate.now(), "mysore");

		List<Patient> dummylist = new ArrayList<>();
		dummylist.add(p1);
		dummylist.add(p2);

		when(repository.findAll()).thenReturn(dummylist);
		List<Patient> output = patientService.viewPatientList();

		verify(repository).findAll();
		assertEquals(dummylist, output);
	}

	@Test
	@DisplayName("test -> delete patients")
	public void deletePatient() throws PatientIdFoundNotException {
		Patient p1 = new Patient(20, "805063752", "abc@gmail.com", LocalDate.now(), "bangalore");
		p1.setUserId(2);
		when(repository.findById(2)).thenReturn(Optional.of(p1));

		Patient p = patientService.deletePatient(2);
		verify(repository).deleteById(2);
		assertEquals(p1, p);

	}

	@Test
	@DisplayName("test -> delete patients with invalid entries")
	public void deletePatientInvalid() throws PatientIdFoundNotException {
		Patient p1 = new Patient(20, "805063752", "abc@gmail.com", LocalDate.now(), "bangalore");
		p1.setUserId(2);
		when(repository.findById(2)).thenReturn(Optional.of(p1));
		Executable executable = () -> patientService.deletePatient(3);
		assertThrows(PatientIdFoundNotException.class, executable);

	}

	@Test
	@DisplayName("test -> add patient")
	public void addPatient() {
		Patient p1 = new Patient(20, "805063752", "abc@gmail.com", LocalDate.now(), "bangalore");
		
		
		when(repository.save(p1)).thenReturn(p1);
		Patient p = patientService.addPatient(p1);
		verify(repository).save(p1);
		assertEquals(p1, p);
	}
	
	@Test
	@DisplayName("test -> update patient with valid entries")
	public void updatePatient() throws PatientIdFoundNotException
	{
		Patient p1 = new Patient(20, "805063752", "abc@gmail.com", LocalDate.now(), "bangalore");
		p1.setUserId(2);
		
		when(repository.findById(2)).thenReturn(Optional.of(p1));

		Patient p = patientService.updatePatient(p1);
		verify(repository).save(p1);
		assertEquals(p1, p);
	}
	
	@Test
	@DisplayName("test -> update patient with invalid entries")
	public void updatePatientInvalid() throws PatientIdFoundNotException
	{
		Patient p1 = new Patient(20, "805063752", "abc@gmail.com", LocalDate.now(), "bangalore");
		p1.setUserId(2);
		
		when(repository.findById(3)).thenReturn(Optional.of(p1));
		Executable executable = () ->  patientService.updatePatient(p1);
		assertThrows(PatientIdFoundNotException.class, executable);

		
		
	}
}
