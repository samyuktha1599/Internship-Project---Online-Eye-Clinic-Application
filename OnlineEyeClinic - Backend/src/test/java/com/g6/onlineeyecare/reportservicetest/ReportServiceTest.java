package com.g6.onlineeyecare.reportservicetest;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.g6.onlineeyecare.test.dto.Test;
import com.g6.onlineeyecare.exceptions.PatientIdFoundNotException;
import com.g6.onlineeyecare.exceptions.ReportIdNotFoundException;
import com.g6.onlineeyecare.exceptions.TestIdNotFoundException;
import com.g6.onlineeyecare.patient.dao.IPatientRepository;
import com.g6.onlineeyecare.patient.dto.Patient;
import com.g6.onlineeyecare.patient.service.IPatientService;
import com.g6.onlineeyecare.patient.service.PatientServiceImpl;
import com.g6.onlineeyecare.report.dao.IReportRepository;
import com.g6.onlineeyecare.report.dto.Report;
import com.g6.onlineeyecare.report.service.ReportServiceImpl;
import com.g6.onlineeyecare.spectacles.dao.ISpectaclesRepository;
import com.g6.onlineeyecare.spectacles.service.ISpectaclesService;
import com.g6.onlineeyecare.spectacles.service.SpectaclesServiceImpl;
import com.g6.onlineeyecare.test.dao.ITestRepository;
import com.g6.onlineeyecare.test.service.ITestService;
import com.g6.onlineeyecare.test.service.TestServiceImpl;

@SpringBootTest
public class ReportServiceTest {

	IReportRepository repository;
	IPatientRepository patientRepository;
	ITestRepository testRepository;
	ISpectaclesRepository spectaclesRepository;

	@InjectMocks
	static ReportServiceImpl reportService;
	static ISpectaclesService spectacleService;
	static IPatientService patientService;
	static ITestService testService;
	private static AutoCloseable ac;

	@Before
	public void doinit() {
		repository = mock(IReportRepository.class);
		patientRepository = mock(IPatientRepository.class);
		testRepository = mock(ITestRepository.class);
		spectaclesRepository = mock(ISpectaclesRepository.class);

		reportService = new ReportServiceImpl(repository);
		spectacleService = new SpectaclesServiceImpl(spectaclesRepository);
		patientService = new PatientServiceImpl(patientRepository);
		testService = new TestServiceImpl(testRepository);

		ac = MockitoAnnotations.openMocks(this);
	}

	@AfterEach
	public void doAtEnd() throws Exception {
		ac.close();
	}



	@org.junit.Test
	@DisplayName("test -> add a report")
	public void addReport() throws TestIdNotFoundException, PatientIdFoundNotException {

		Patient p = new Patient();
		p.setUserId(1);
		when(patientRepository.findById(1)).thenReturn(Optional.of(p));
		Test t = new Test();
		t.setTestId(2);
		when(testRepository.findById(2)).thenReturn(Optional.of(t));

		Report r = new Report( LocalDate.of(2002, 02, 12), "report description", "visualacuity", "visualacuitynear",
				"visualacuitydistance", t, p);
		

		when(repository.save(r)).thenReturn(r);
		Report actualR = reportService.addReport(r);
		verify(repository).save(r);
		assertEquals(r, actualR);
	}

	@org.junit.Test
	@DisplayName("test -> add a report with invalid entries")
	public void addReportInvalid() throws TestIdNotFoundException, PatientIdFoundNotException {

		Patient p = mock(Patient.class);
		Test t = mock(Test.class);

		Report r = new Report( LocalDate.of(2002, 02, 12), "report description", "visualacuity", "visualacuitynear",
				"visualacuitydistance", t, p);
		

		when(repository.save(r)).thenReturn(r);
		Executable executable = () -> reportService.addReport(r);
		assertThrows(PatientIdFoundNotException.class, executable);
	}

	@org.junit.Test
	@DisplayName("test -> delete a report")
	public void deleteReport() throws ReportIdNotFoundException {

		Patient p1 = new Patient();
		Test test = new Test();

		Report r = new Report(LocalDate.of(2002, 02, 12), "report description", "visualacuity", "visualacuitynear",
				"visualacuitydistance", test, p1);
		when(repository.findById(1)).thenReturn(Optional.of(r));
		Report actualR = reportService.removeReport(1);
		verify(repository).findById(1);
		assertEquals(r, actualR);
	}

	@org.junit.Test
	@DisplayName("test -> delete a report with invalid entries")
	public void deleteReportInvlaid() throws ReportIdNotFoundException {

		Patient p1 = new Patient();
		Test test = new Test();

		Report r = new Report(LocalDate.of(2002, 02, 12), "report description", "visualacuity", "visualacuitynear",
				"visualacuitydistance", test, p1);
		when(repository.findById(1)).thenReturn(Optional.of(r));
		Executable executable = () -> reportService.removeReport(2);
		assertThrows(ReportIdNotFoundException.class, executable);
	}
	
	@org.junit.Test
	@DisplayName("test -> update a report with valid entries")
	public void updateReport() throws ReportIdNotFoundException
	{
		Patient p1 = new Patient();
		Test test = new Test();

		Report r = new Report(LocalDate.of(2002, 02, 12), "report description", "visualacuity", "visualacuitynear",
				"visualacuitydistance", test, p1);
		r.setReportId(1);
		when(repository.findById(1)).thenReturn(Optional.of(r));
		Report actualR = reportService.updateReport(r);
		verify(repository).save(r);
		assertEquals(r, actualR);
		
	}
	
	@org.junit.Test
	@DisplayName("test -> update a report with invalid entries")
	public void updateReportInvalid() throws ReportIdNotFoundException
	{
		Patient p1 = new Patient();
		Test test = new Test();

		Report r = new Report(LocalDate.of(2002, 02, 12), "report description", "visualacuity", "visualacuitynear",
				"visualacuitydistance", test, p1);
		r.setReportId(1);
		when(repository.findById(10)).thenReturn(Optional.of(r));
		Executable executable = () -> reportService.updateReport(r);
		assertThrows(ReportIdNotFoundException.class, executable);
		
	}
}
