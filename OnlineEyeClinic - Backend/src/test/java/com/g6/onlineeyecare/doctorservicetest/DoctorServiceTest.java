package com.g6.onlineeyecare.doctorservicetest;

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
import com.g6.onlineeyecare.doctor.dao.IDoctorRepository;
import com.g6.onlineeyecare.doctor.dto.Doctor;
import com.g6.onlineeyecare.doctor.service.DoctorServiceImpl;
import com.g6.onlineeyecare.exceptions.DoctorIdNotFoundException;
import com.g6.onlineeyecare.test.dao.ITestRepository;
import com.g6.onlineeyecare.test.service.ITestService;
import com.g6.onlineeyecare.test.service.TestServiceImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DoctorServiceTest {

	IDoctorRepository repository;
	IAppointmentRepository appointmentRepository;
	ITestRepository testRepository;

	@InjectMocks
	static DoctorServiceImpl doctorService;
	static IAppointmentService appointmentService;
	static ITestService testService;
	private static AutoCloseable ac;

	@Before
	public void doinit() {
		repository = mock(IDoctorRepository.class);
		appointmentRepository = mock(IAppointmentRepository.class);
		testRepository = mock(ITestRepository.class);

		doctorService = new DoctorServiceImpl(repository);
		appointmentService = new AppointmentServiceImpl(appointmentRepository);
		testService = new TestServiceImpl(testRepository);

		ac = MockitoAnnotations.openMocks(this);
	}

	@AfterEach
	public void doAtEnd() throws Exception {
		ac.close();
	}

	@Test
	@DisplayName("test -> view all doctors")
	public void ViewAllDoctor() {
		Doctor d1 = new Doctor("11:30:00", "635241589", "abc@gmail.com", "bangalore");
		Doctor d2 = new Doctor("12:30:00", "786523496", "xyz@gmail.com", "mysore");

		List<Doctor> dummylist = new ArrayList<>();
		dummylist.add(d1);
		dummylist.add(d2);

		when(repository.findAll()).thenReturn(dummylist);
		List<Doctor> output = doctorService.viewDoctorsList();

		verify(repository).findAll();
		assertEquals(dummylist, output);
	}

	@Test
	@DisplayName("test -> view doctor by Id")
	public void testViewDoctorById() throws DoctorIdNotFoundException {
		Doctor d1 = new Doctor("11:30:00", "635241589", "abc@gmail.com", "bangalore");
		d1.setUserId(2);

		when(repository.findById(2)).thenReturn(Optional.of(d1));
		Doctor d = doctorService.viewDoctor(2);
		verify(repository).findById(2);
		assertEquals(d1, d);
	}

	@Test
	@DisplayName("test -> view doctor by Id using invalid entries")
	public void ViewDoctorByIdNotExisting() throws DoctorIdNotFoundException {
		Doctor d1 = new Doctor("11:30:00", "635241589", "abc@gmail.com", "bangalore");
		d1.setUserId(2);
		when(repository.findById(2)).thenReturn(Optional.of(d1));
		Executable executable = () -> doctorService.viewDoctor(3);
		assertThrows(DoctorIdNotFoundException.class, executable);

	}

	@Test
	@DisplayName("test -> add doctor")
	public void addDoctor() {
		Doctor d1 = new Doctor("11:30:00", "635241589", "abc@gmail.com", "bangalore");
		d1.setUserId(2);

		when(repository.save(d1)).thenReturn(d1);
		Doctor d = doctorService.addDoctor(d1);
		verify(repository).save(d1);
		assertEquals(d1, d);
	}

	@Test
	@DisplayName("test -> delete doctor by Id")
	public void deleteDoctor() throws DoctorIdNotFoundException {
		Doctor d1 = new Doctor("11:30:00", "635241589", "abc@gmail.com", "bangalore");
		d1.setUserId(2);
		when(repository.findById(2)).thenReturn(Optional.of(d1));

		Doctor d2 = doctorService.deleteDoctor(2);
		verify(repository).deleteById(2);
		assertEquals(d1, d2);
	}

	@Test
	@DisplayName("test -> delete doctor by Id with invalid entries")
	public void deleteDoctorInvalid() throws DoctorIdNotFoundException {
		Doctor d1 = new Doctor("11:30:00", "635241589", "abc@gmail.com", "bangalore");
		d1.setUserId(2);
		when(repository.findById(2)).thenReturn(Optional.of(d1));

		Executable executable = () -> doctorService.deleteDoctor(3);
		assertThrows(DoctorIdNotFoundException.class, executable);
	}
	
	@Test
	@DisplayName("test -> update doctor with valid entries")
	public void updateDoctor() throws DoctorIdNotFoundException
	{
		Doctor d1 = new Doctor("11:30:00", "635241589", "abc@gmail.com", "bangalore");
		d1.setUserId(2);
		
		when(repository.findById(2)).thenReturn(Optional.of(d1));
		Doctor actual=doctorService.updateDoctor(d1);
		verify(repository).save(d1);
		assertEquals(d1, actual);
	}
	
	@Test
	@DisplayName("test -> update doctor with invalid entries")
	public void updateDoctorInvalid() throws DoctorIdNotFoundException
	{
		Doctor d1 = new Doctor("11:30:00", "635241589", "abc@gmail.com", "bangalore");
		d1.setUserId(2);
		
		when(repository.findById(3)).thenReturn(Optional.of(d1));
		Executable executable = () -> doctorService.updateDoctor(d1);
		assertThrows(DoctorIdNotFoundException.class, executable);
		
		
	}
}
