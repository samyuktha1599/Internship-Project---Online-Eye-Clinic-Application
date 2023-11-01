package com.g6.onlineeyecare.patient.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.g6.onlineeyecare.appointment.dto.Appointment;
import com.g6.onlineeyecare.appointment.dto.AppointmentDTO;
import com.g6.onlineeyecare.appointment.dto.AppointmentResponseDTO;

import com.g6.onlineeyecare.exceptions.AppointmentIdNotFoundException;
import com.g6.onlineeyecare.exceptions.DoctorIdNotFoundException;
import com.g6.onlineeyecare.exceptions.PatientIdFoundNotException;
import com.g6.onlineeyecare.patient.dto.Patient;
import com.g6.onlineeyecare.patient.dto.PatientDTO;
import com.g6.onlineeyecare.patient.dto.PatientResponseDTO;
import com.g6.onlineeyecare.patient.service.IPatientService;
import com.g6.onlineeyecare.report.dto.Report;
import com.g6.onlineeyecare.report.dto.ReportResponseDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Validated
@Api(value = "Patient Rest Controller")
@RestController
@RequestMapping("/patient")
@CrossOrigin(origins = {"http://localhost:9999","http://localhost:4200"}, allowedHeaders = "*")
public class PatientController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	IPatientService patientService;

	@ApiOperation(value = "create a new Patient profile", response = Patient.class)
	@PostMapping("/add")
	public ResponseEntity<PatientResponseDTO> addPatient(@RequestBody @Valid PatientDTO patient) {

		Patient actual = modelMapper.map(patient, Patient.class);
		PatientResponseDTO response = modelMapper.map(this.patientService.addPatient(actual), PatientResponseDTO.class);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "Update your profile ", response = Patient.class)
	@PutMapping("/update")
	public ResponseEntity<PatientResponseDTO> updatePatient(@RequestBody PatientDTO patient)
			throws PatientIdFoundNotException {
		Patient actual = modelMapper.map(patient, Patient.class);
		PatientResponseDTO response = modelMapper.map(this.patientService.updatePatient(actual),
				PatientResponseDTO.class);
		if (response != null) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

	}

	@ApiOperation(value = "Delete your profile ", response = Patient.class)
	@DeleteMapping("/delete/{patientId}")
	public ResponseEntity<PatientResponseDTO> deletePatient(@PathVariable("patientId") int patientId)
			throws PatientIdFoundNotException {

		PatientResponseDTO response = modelMapper.map(this.patientService.deletePatient(patientId),
				PatientResponseDTO.class);
		if (response != null) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "view Patient profile by Id", response = Patient.class)
	@GetMapping("/view/{patientId}")
	public ResponseEntity<PatientResponseDTO> viewPatient(@PathVariable("patientId") int patientId)
			throws PatientIdFoundNotException {

		PatientResponseDTO response = modelMapper.map(this.patientService.viewPatient(patientId),
				PatientResponseDTO.class);
		if (response != null) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "view list of Patients ", response = Patient.class)
	@GetMapping("/viewAll")
	public ResponseEntity<List<PatientResponseDTO>> viewPatientList() {
		List<Patient> patientList = this.patientService.viewPatientList();
		List<PatientResponseDTO> patientDtoList = new ArrayList<>();
		for (Patient p : patientList) {
			PatientResponseDTO patientDto = modelMapper.map(p, PatientResponseDTO.class);
			patientDtoList.add(patientDto);

		}
		if (!(patientDtoList.isEmpty())) {
			return new ResponseEntity<>(patientDtoList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(patientDtoList, HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "Book appointment to consult the doctor", response = Appointment.class)
	@PostMapping("/book")
	public ResponseEntity<AppointmentResponseDTO> bookAppointment(@RequestBody AppointmentDTO appointment)
			throws DoctorIdNotFoundException, PatientIdFoundNotException {

		Appointment app = modelMapper.map(appointment, Appointment.class);
		AppointmentResponseDTO response = modelMapper.map(this.patientService.bookAppointment(app),
				AppointmentResponseDTO.class);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "Get the required appointment by Id ", response = Appointment.class)
	@GetMapping("/viewAppointment/{appointmentId}")
	public ResponseEntity<AppointmentResponseDTO> viewAppointmentDetails(
			@PathVariable("appointmentId") int appointmentid) throws AppointmentIdNotFoundException {

		AppointmentResponseDTO response = modelMapper.map(this.patientService.viewAppointmentDetails(appointmentid),
				AppointmentResponseDTO.class);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "Get the required Report by patientId ", response = Report.class)
	@GetMapping("/report/{patientId}")
	public ResponseEntity<List<ReportResponseDTO>> viewReport(@PathVariable("patientId") int patientId)
			throws PatientIdFoundNotException {

		List<Report> reportList = this.patientService.viewReport(patientId);
		List<ReportResponseDTO> reportDtoList = new ArrayList<>();
		for (Report r : reportList) {

			ReportResponseDTO response = modelMapper.map(r, ReportResponseDTO.class);
			reportDtoList.add(response);

		}
		if (!(reportDtoList.isEmpty())) {
			return new ResponseEntity<>(reportDtoList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(reportDtoList, HttpStatus.BAD_REQUEST);
		}

	}

	@ApiOperation(value = "Get the required patient by name ", response = Patient.class)
	@GetMapping("/viewByName/{patientName}")
	public ResponseEntity<List<PatientResponseDTO>> viewPatientByName(@PathVariable("patientName") String patientName) {
		List<Patient> patientList = this.patientService.viewPatientByName(patientName);
		List<PatientResponseDTO> patientDtoList = new ArrayList<>();
		for (Patient a : patientList) {
			PatientResponseDTO patientDto = modelMapper.map(a, PatientResponseDTO.class);
			patientDtoList.add(patientDto);
		}
		if (!(patientDtoList.isEmpty())) {
			return new ResponseEntity<>(patientDtoList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(patientDtoList, HttpStatus.BAD_REQUEST);
		}
	}

}