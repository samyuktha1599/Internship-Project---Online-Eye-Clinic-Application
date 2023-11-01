package com.g6.onlineeyecare.testservicetest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.g6.onlineeyecare.test.dto.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.g6.onlineeyecare.exceptions.PatientIdFoundNotException;
import com.g6.onlineeyecare.exceptions.TestIdNotFoundException;
import com.g6.onlineeyecare.patient.dao.IPatientRepository;
import com.g6.onlineeyecare.patient.dto.Patient;
import com.g6.onlineeyecare.patient.service.IPatientService;
import com.g6.onlineeyecare.patient.service.PatientServiceImpl;
import com.g6.onlineeyecare.test.dao.ITestRepository;
import com.g6.onlineeyecare.test.service.TestServiceImpl;

@SpringBootTest
public class TestServiceTest {

	ITestRepository repository;
	IPatientRepository patientRepository;

	@InjectMocks
	static TestServiceImpl testService;
	static IPatientService patientService;
	private static AutoCloseable ac;

	@Before
	public void doinit() {
		repository = mock(ITestRepository.class);
		patientRepository = mock(IPatientRepository.class);

		testService = new TestServiceImpl(repository);
		patientService = new PatientServiceImpl(patientRepository);

		ac = MockitoAnnotations.openMocks(this);
	}

	@AfterEach
	public void doAtEnd() throws Exception {
		ac.close();
	}

	@org.junit.Test
	@DisplayName("view all test")
	public void ViewAllTest() {

		Patient p = new Patient(20, "259751", "ram@gmail.com", LocalDate.of(2002, 02, 12), "Bangalore");
		Test test = new Test(1, "abc", "vision", "describe", 1800, p);

		List<Test> list = new ArrayList<>();
		list.add(test);

		when(repository.findAll()).thenReturn(list);
		List<Test> output = testService.viewAllTests();

		verify(repository).findAll();
		assertIterableEquals(list, output);
		verify(repository).findAll();
	}

	@org.junit.Test
	@DisplayName("test -> view test by testId")
	public void testViewTestById() throws TestIdNotFoundException {

		Test t = new Test();
		t.setTestId(1);

		when(repository.findById(1)).thenReturn(Optional.of(t));
		Test test = testService.viewTest(1);
		assertEquals(t, test);
		verify(repository).findById(1);
	}

	@org.junit.Test
	@DisplayName("test -> view test by testId with invalid entries")
	public void testViewTestByIdInvalid() throws TestIdNotFoundException {

		Test t = new Test();
		t.setTestId(1);

		when(repository.findById(1)).thenReturn(Optional.of(t));
		Executable executable = () -> testService.viewTest(2);
		assertThrows(TestIdNotFoundException.class, executable);
	}

	@org.junit.Test
	@DisplayName("test -> adding a  test with valid entries")
	public void addTest() throws PatientIdFoundNotException {

		Patient p = new Patient(20, "259751", "ram@gmail.com", LocalDate.of(2002, 02, 12), "Bangalore");
		p.setUserId(1);

		when(patientRepository.findById(p.getUserId())).thenReturn(Optional.of(p));

		Test test = new Test(2, "abc", "vision", "describe", 1800, p);

		when(repository.save(test)).thenReturn(test);

		Test actucalTest = testService.addTest(test);
		assertNotNull(actucalTest);
		assertEquals(test, actucalTest);
		verify(repository).save(test);
	}

	@org.junit.Test
	@DisplayName("test -> adding a  test with invalid entries")
	public void addTestInvalidEntries() throws PatientIdFoundNotException {

		Patient p = mock(Patient.class);
		Test test = new Test(2, "abc", "vision", "describe", 1800, p);

		when(repository.save(test)).thenReturn(test);
		Executable executable = () -> testService.addTest(test);
		assertThrows(PatientIdFoundNotException.class, executable);
	}

	@org.junit.Test
	@DisplayName("test -> delete a test with valid entries")
	public void deleteTest() throws TestIdNotFoundException {

		Test test = new Test();
		test.setTestId(1);
		when(repository.findById(1)).thenReturn(Optional.of(test));

		Test t = testService.removeTest(1);
		assertEquals(test, t);
		verify(repository).deleteById(1);

	}

	@org.junit.Test
	@DisplayName("test -> delete a test with invalid entries")
	public void deleteTestIdException() throws TestIdNotFoundException {
		Test test = new Test();
		test.setTestId(1);
		when(repository.findById(1)).thenReturn(Optional.of(test));
		Executable executable = () -> testService.removeTest(2);
		assertThrows(TestIdNotFoundException.class, executable);

	}
	
	@org.junit.Test
	@DisplayName("test -> update a test with valid entries")
	public void UpdateTest() throws TestIdNotFoundException {
		Test test = new Test();
		test.setTestId(1);
		when(repository.findById(1)).thenReturn(Optional.of(test));

		Test t = testService.updateTest(test);
		assertEquals(test, t);
		verify(repository).save(test);

	}
	
	@org.junit.Test
	@DisplayName("test -> update a test with invalid entries")
	public void UpdateTestInvalid() throws TestIdNotFoundException {
		Test test = new Test();
		test.setTestId(1);
		when(repository.findById(10)).thenReturn(Optional.of(test));
		Executable executable = () ->testService.updateTest(test);
		assertThrows(TestIdNotFoundException.class, executable);
		

	}

}
