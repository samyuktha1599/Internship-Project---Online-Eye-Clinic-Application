package com.g6.onlineeyecare.report.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.g6.onlineeyecare.exceptions.PatientIdFoundNotException;
import com.g6.onlineeyecare.exceptions.ReportIdNotFoundException;
import com.g6.onlineeyecare.exceptions.TestIdNotFoundException;
import com.g6.onlineeyecare.patient.dao.IPatientRepository;
import com.g6.onlineeyecare.patient.dto.Patient;
import com.g6.onlineeyecare.report.dao.IReportRepository;
import com.g6.onlineeyecare.report.dto.Report;
import com.g6.onlineeyecare.spectacles.dto.Spectacles;
import com.g6.onlineeyecare.spectacles.service.ISpectaclesService;
import com.g6.onlineeyecare.test.dao.ITestRepository;

@Service
public class ReportServiceImpl implements IReportService {

	@Autowired
	IReportRepository repository;
	@Autowired
	ISpectaclesService spectacleService;
	@Autowired
	IPatientRepository patientRepository;
	@Autowired
	ITestRepository testRepository;

	Logger log = LoggerFactory.getLogger(ReportServiceImpl.class);

	public ReportServiceImpl(IReportRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	@Transactional
	public Report addReport(Report report) throws TestIdNotFoundException, PatientIdFoundNotException {
		
		if (patientRepository.findById(report.getPatient().getUserId()).isPresent()) {
			if (testRepository.findById(report.getTypeOfTest().getTestId()).isPresent()) {
				repository.save(report);
			} else {
				throw new TestIdNotFoundException("test Id not found");
			}
		} else {
			throw new PatientIdFoundNotException("patient Id not found");
		}
		return report;
	}

	@Override
	@Transactional
	public Report updateReport(Report report) throws ReportIdNotFoundException {
		
		Optional<Report> optional = repository.findById(report.getReportId());
		if (optional.isPresent()) {
			repository.save(report);
		} else {
			throw new ReportIdNotFoundException("Report ID not found for updation");
		}
		return optional.get();
	}

	@Override
	@Transactional
	public Report removeReport(int reportId) throws ReportIdNotFoundException {

		Optional<Report> optional = repository.findById(reportId);
		if (optional.isPresent()) {
			repository.deleteById(reportId);
		} else {
			throw new ReportIdNotFoundException("Report ID not found for deletion");
		}
		return optional.get();
	}

	@Override
	public Report viewReport(int reportId, int patientId) throws ReportIdNotFoundException, PatientIdFoundNotException {
		Report report = null;
		Optional<Patient> optional1 = this.patientRepository.findById(patientId);
		if (optional1.isPresent()) {
			Optional<Report> optional2 = this.repository.findById(reportId);
			if (optional2.isPresent()) {
				report = repository.findReportByPatiendIdandReportId(reportId, patientId);
			} else {
				throw new ReportIdNotFoundException("Report not found for given report Id");
			}
		} else {
			throw new PatientIdFoundNotException("Report not found for given patient Id");
		}
		return report;
	}

	@Override
	public List<Report> viewAllReport(LocalDate date) {

		List<Report> myReport = new ArrayList<>();

		try {
			myReport = repository.viewReportByDate(date);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return myReport;
	}

	@Override
	public List<Spectacles> viewSpetacles() {
		
		List<Spectacles> spectacleList = new ArrayList<>();
		try {
			spectacleList = spectacleService.viewSpectacles();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return spectacleList;
	}

}
