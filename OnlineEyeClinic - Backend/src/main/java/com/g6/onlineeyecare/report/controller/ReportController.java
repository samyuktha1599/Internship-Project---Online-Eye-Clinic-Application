package com.g6.onlineeyecare.report.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.g6.onlineeyecare.exceptions.PatientIdFoundNotException;
import com.g6.onlineeyecare.exceptions.ReportIdNotFoundException;
import com.g6.onlineeyecare.exceptions.TestIdNotFoundException;
import com.g6.onlineeyecare.report.dto.Report;
import com.g6.onlineeyecare.report.dto.ReportDTO;
import com.g6.onlineeyecare.report.dto.ReportResponseDTO;
import com.g6.onlineeyecare.report.service.IReportService;
import com.g6.onlineeyecare.spectacles.dto.Spectacles;
import com.g6.onlineeyecare.spectacles.dto.SpectaclesResponseDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Validated
@Api(value = "Report Rest Controller")
@RestController
@RequestMapping("/report")
@CrossOrigin(origins = {"http://localhost:9999","http://localhost:4200"}, allowedHeaders = "*")
public class ReportController {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	IReportService reportService;

	@ApiOperation(value = "Generate a new report", response = Report.class)
	@PostMapping("/add")
	public ResponseEntity<ReportResponseDTO> addReport(@RequestBody @Valid ReportDTO report)
			throws TestIdNotFoundException, PatientIdFoundNotException {
		
		Report actual = modelMapper.map(report, Report.class);
		ReportResponseDTO response = modelMapper.map(this.reportService.addReport(actual), ReportResponseDTO.class);

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@ApiOperation(value = "Update the specific report", response = Report.class)
	@PutMapping("/update")
	public ResponseEntity<ReportResponseDTO> updateReport(@RequestBody ReportDTO report) throws ReportIdNotFoundException {
		
		Report actual = modelMapper.map(report, Report.class);
		ReportResponseDTO response = modelMapper.map(this.reportService.updateReport(actual), ReportResponseDTO.class);
		if (response != null) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "Delete the report", response = Report.class)
	@DeleteMapping("/delete/{reportId}")
	public ResponseEntity<ReportResponseDTO> deleteReport(@PathVariable("reportId") int reportId)
			throws ReportIdNotFoundException {
		
		ReportResponseDTO response = modelMapper.map(this.reportService.removeReport(reportId), ReportResponseDTO.class);
		if (response != null) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

	}

	@ApiOperation(value = "View the specific report by report id and patient id", response = Report.class)
	@GetMapping("/view/{reportId}/{patientId}")
	public ResponseEntity<ReportResponseDTO> viewReport(@PathVariable("reportId") int reportId,
			@PathVariable("patientId") int patientId) throws ReportIdNotFoundException, PatientIdFoundNotException {
		
		ReportResponseDTO response = modelMapper.map(this.reportService.viewReport(reportId, patientId),ReportResponseDTO.class);
		if (response != null) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

	}

	@ApiOperation(value = "View the specific report by date", response = Report.class)
	@GetMapping("/viewByDate/{date}")
	public ResponseEntity<List<ReportResponseDTO>> viewAllReport(
			@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
		List<Report> reportList = this.reportService.viewAllReport(date);
		List<ReportResponseDTO> reportDtoList = new ArrayList<>();
		for (Report r : reportList) {
			ReportResponseDTO reportDto = modelMapper.map(r, ReportResponseDTO.class);
			reportDtoList.add(reportDto);
		}
		if (!(reportDtoList.isEmpty())) {
			return new ResponseEntity<>(reportDtoList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(reportDtoList, HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "Get the list of Spectacles", response = Spectacles.class)
	@GetMapping("/spectacles")
	public ResponseEntity<List<SpectaclesResponseDTO>> viewSpetacles() {
		
		List<Spectacles> spectaclesList = this.reportService.viewSpetacles();
		List<SpectaclesResponseDTO> spectaclesDtoList = new ArrayList<>();
		for (Spectacles s : spectaclesList) {
			SpectaclesResponseDTO spectaclesDto = modelMapper.map(s, SpectaclesResponseDTO.class);
			spectaclesDtoList.add(spectaclesDto);
		}
		if (!(spectaclesDtoList.isEmpty())) {
			return new ResponseEntity<>(spectaclesDtoList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(spectaclesDtoList, HttpStatus.BAD_REQUEST);
		}
	}

}