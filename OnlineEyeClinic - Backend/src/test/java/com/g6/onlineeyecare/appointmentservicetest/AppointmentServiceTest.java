package com.g6.onlineeyecare.appointmentservicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
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
import com.g6.onlineeyecare.appointment.dto.Appointment;
import com.g6.onlineeyecare.appointment.service.AppointmentServiceImpl;
import com.g6.onlineeyecare.doctor.dao.IDoctorRepository;
import com.g6.onlineeyecare.doctor.dto.Doctor;
import com.g6.onlineeyecare.doctor.service.DoctorServiceImpl;
import com.g6.onlineeyecare.doctor.service.IDoctorService;
import com.g6.onlineeyecare.exceptions.AppointmentIdNotFoundException;
import com.g6.onlineeyecare.exceptions.DoctorIdNotFoundException;
import com.g6.onlineeyecare.exceptions.InvalidAppointmentException;
import com.g6.onlineeyecare.exceptions.PatientIdFoundNotException;
import com.g6.onlineeyecare.patient.dao.IPatientRepository;
import com.g6.onlineeyecare.patient.dto.Patient;
import com.g6.onlineeyecare.patient.service.IPatientService;
import com.g6.onlineeyecare.patient.service.PatientServiceImpl;

@SpringBootTest
public class AppointmentServiceTest {

	IAppointmentRepository repository;
	IPatientRepository patientRepository;
	IDoctorRepository doctorRepository;

	@InjectMocks
	static AppointmentServiceImpl appointmentService;
	static IDoctorService doctorService;
	static IPatientService patientService;
	private static AutoCloseable ac;

	@Before
	public void doinit() {
		repository = mock(IAppointmentRepository.class);
		patientRepository = mock(IPatientRepository.class);
		doctorRepository = mock(IDoctorRepository.class);

		appointmentService = new AppointmentServiceImpl(repository);
		doctorService = new DoctorServiceImpl(doctorRepository);
		patientService = new PatientServiceImpl(patientRepository);

		ac = MockitoAnnotations.openMocks(this);
	}

	@AfterEach
	public void doAtEnd() throws Exception {
		ac.close();
	}

	@Test
	@DisplayName("test -> view all appointments")
	public void testViewAllAppointments() {
		Appointment appointment = new Appointment();
		appointment.setAppointmentId(1);
		Appointment appointment2 = new Appointment();
		appointment.setAppointmentId(2);
		List<Appointment> list = new ArrayList<Appointment>();
		list.add(appointment);
		list.add(appointment2);

		when(repository.findAll()).thenReturn(list);
		List<Appointment> actualList = appointmentService.viewAllAppointments();
		verify(repository).findAll();
		assertEquals(list, actualList);
	}

	@Test
	@DisplayName("test -> for view by Id for valid credentials")
	public void testViewById() throws AppointmentIdNotFoundException {

		Appointment appointment = new Appointment();
		appointment.setAppointmentId(1);

		when(repository.findById(1)).thenReturn(Optional.of(appointment));
		Appointment a = appointmentService.viewAppointment(1);
		verify(repository).findById(appointment.getAppointmentId());
		assertEquals(appointment, a);
	}

	@Test
	@DisplayName("test -> for view by Id for invalid credentials")
	public void testViewByIdforInvalidEntries() throws AppointmentIdNotFoundException {

		Appointment appointment = new Appointment();
		appointment.setAppointmentId(1);

		when(repository.findById(1)).thenReturn(Optional.of(appointment));
		Executable executable = () -> appointmentService.viewAppointment(2);
		assertThrows(AppointmentIdNotFoundException.class, executable);

	}

	@Test
	@DisplayName("test -> for booking an appointment with valid entries")
	public void testBookAppointment() throws DoctorIdNotFoundException, PatientIdFoundNotException {
		Patient p = new Patient();
		p.setUserId(1);

		when(patientRepository.findById(p.getUserId())).thenReturn(Optional.of(p));

		Doctor d = new Doctor();
		d.setUserId(5);


		when(doctorRepository.findById(d.getUserId())).thenReturn(Optional.of(d));

		Appointment appointment = new Appointment(10, LocalDate.now(), LocalTime.now(), d, p);

		when(repository.save(appointment)).thenReturn(appointment);

		Appointment actualAppointment = appointmentService.bookAppointment(appointment);

		assertEquals(appointment, actualAppointment);

		verify(repository).save(appointment);

	}

	@Test
	@DisplayName("test -> for booking an appointment with invalid entries")
	public void testBookAppointmentInvalidEntries() throws DoctorIdNotFoundException, PatientIdFoundNotException {
		Patient p = mock(Patient.class);
		Doctor d = mock(Doctor.class);
		Appointment appointment = new Appointment(10, LocalDate.now(), LocalTime.now(), d, p);

		when(repository.save(appointment)).thenReturn(appointment);
		Executable executable = () -> appointmentService.bookAppointment(appointment);
		assertThrows(DoctorIdNotFoundException.class, executable);
	}

	@Test
	@DisplayName("test ->  Cancel appointment")
	public void testCancelAppointment() throws AppointmentIdNotFoundException {
		Appointment appointment = new Appointment();
		appointment.setAppointmentId(1);

		when(repository.findById(1)).thenReturn(Optional.of(appointment));
		Appointment actualAppointment = appointmentService.cancelAppointment(1);
		verify(repository).findById(1);
		assertEquals(appointment, actualAppointment);

	}

	@Test
	@DisplayName("test -> Cancel appointment with invalid entries")
	public void testCancelAppointmentInvaild() throws AppointmentIdNotFoundException {
		Appointment appointment = new Appointment();
		appointment.setAppointmentId(1);

		when(repository.findById(1)).thenReturn(Optional.of(appointment));
		Executable executable = () -> appointmentService.cancelAppointment(2);
		assertThrows(AppointmentIdNotFoundException.class, executable);

	}
	
	@Test
	@DisplayName("test -> for updating appointment with valid entries")
	public void testUpdateAppointment() throws InvalidAppointmentException
	{
		Appointment appointment = new Appointment();
		appointment.setAppointmentId(1);
		appointment.setStatus("Booked");
		when(repository.findById(1)).thenReturn(Optional.of(appointment));
		Appointment actualAppointment = appointmentService.updateAppointment(appointment);
		verify(repository).save(appointment);
		assertEquals(appointment, actualAppointment);
		
	}
	
	@Test
	@DisplayName("test -> for updating appointment with invalid entries")
	public void testUpdateAppointmentInvalid() throws InvalidAppointmentException
	{
		Appointment appointment = new Appointment();
		appointment.setAppointmentId(1);
		
		when(repository.findById(2)).thenReturn(Optional.of(appointment));
		Executable executable = () -> appointmentService.updateAppointment(appointment);
		assertThrows(InvalidAppointmentException.class, executable);
		
	}
	
	
}
