package com.g6.onlineeyecare.doctor.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.g6.onlineeyecare.appointment.dto.Appointment;
import com.g6.onlineeyecare.appointment.dto.AppointmentResponseDTO;
import com.g6.onlineeyecare.doctor.dto.Doctor;
import com.g6.onlineeyecare.doctor.dto.DoctorResponseDTO;
import com.g6.onlineeyecare.doctor.dto.DoctorDTO;
import com.g6.onlineeyecare.doctor.service.IDoctorService;
import com.g6.onlineeyecare.exceptions.DoctorIdNotFoundException;
import com.g6.onlineeyecare.exceptions.PatientIdFoundNotException;
import com.g6.onlineeyecare.test.dto.Test;
import com.g6.onlineeyecare.test.dto.TestDTO;
import com.g6.onlineeyecare.test.dto.TestResponseDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Validated
@Api(value = "Doctor Rest Controller")
@RestController
@RequestMapping("/doctor")
@CrossOrigin(origins = {"http://localhost:9999","http://localhost:4200"}, allowedHeaders = "*")
public class DoctorController {

	@Autowired
	IDoctorService doctorService;

	@Autowired
	private ModelMapper modelMapper;

	@ApiOperation(value = "Create a new Doctor profile", response = Doctor.class)
	@PostMapping("/add")
	public ResponseEntity<DoctorResponseDTO> addDoctor(@RequestBody @Valid DoctorDTO doctor) {
		Doctor actual = modelMapper.map(doctor, Doctor.class);
		DoctorResponseDTO response = modelMapper.map(this.doctorService.addDoctor(actual), DoctorResponseDTO.class);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "Update your profile", response = Doctor.class)
	@PutMapping("/update")
	public ResponseEntity<DoctorResponseDTO> updateDoctor(@RequestBody DoctorDTO doctor)
			throws DoctorIdNotFoundException {

		Doctor actual = modelMapper.map(doctor, Doctor.class);
		DoctorResponseDTO response = modelMapper.map(this.doctorService.updateDoctor(actual), DoctorResponseDTO.class);
		if (response != null) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "delete your profile ", response = Doctor.class)
	@DeleteMapping("/delete/{doctorId}")
	public ResponseEntity<DoctorResponseDTO> deleteDoctor(@PathVariable("doctorId") int doctorId)
			throws DoctorIdNotFoundException {
		DoctorResponseDTO response = modelMapper.map(this.doctorService.deleteDoctor(doctorId),
				DoctorResponseDTO.class);
		if (response != null) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "View Doctor profile by Id", response = Doctor.class)
	@GetMapping("/view/{doctorId}")
	public ResponseEntity<DoctorResponseDTO> viewDoctor(@PathVariable("doctorId") int doctorId)
			throws DoctorIdNotFoundException {
		DoctorResponseDTO response = modelMapper.map(this.doctorService.viewDoctor(doctorId), DoctorResponseDTO.class);
		if (response != null) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

	}

	@ApiOperation(value = "Get the List of Doctors", response = Doctor.class)
	@GetMapping("/viewAll")
	public ResponseEntity<List<DoctorResponseDTO>> viewDoctorsList() {
		List<Doctor> docList = this.doctorService.viewDoctorsList();
		List<DoctorResponseDTO> docDtoList = new ArrayList<>();
		for (Doctor d : docList) {
			DoctorResponseDTO docDto = modelMapper.map(d, DoctorResponseDTO.class);
			docDtoList.add(docDto);
		}
		if (!(docDtoList.isEmpty())) {
			return new ResponseEntity<>(docDtoList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(docDtoList, HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "view all appointments", response = Doctor.class)
	@GetMapping("/viewAppointments/{date}/{doctorName}")
	public ResponseEntity<List<AppointmentResponseDTO>> viewAppointments(
			@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
			@PathVariable("doctorName") String doctorName) {

		List<Appointment> appointmentList = this.doctorService.viewAppointments(doctorName, date);
		List<AppointmentResponseDTO> appointmentDtoList = new ArrayList<>();
		for (Appointment a : appointmentList) {
			AppointmentResponseDTO appointmentDto = modelMapper.map(a, AppointmentResponseDTO.class);
			appointmentDtoList.add(appointmentDto);
		}
		if (!(appointmentDtoList.isEmpty())) {
			return new ResponseEntity<>(appointmentDtoList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(appointmentDtoList, HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "Create a new test", response = Doctor.class)
	@PostMapping("/test")
	public ResponseEntity<TestResponseDTO> createTest(@RequestBody TestDTO test) throws PatientIdFoundNotException {

		Test actucal = modelMapper.map(test, Test.class);
		TestResponseDTO response = modelMapper.map(this.doctorService.createTest(actucal), TestResponseDTO.class);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "Get the required doctor by name ", response = Doctor.class)
	@GetMapping("/viewByName/{doctorName}")
	public ResponseEntity<List<DoctorResponseDTO>> viewDoctorByName(@PathVariable("doctorName") String doctorName) {
		List<Doctor> doctorList = this.doctorService.viewDoctorByName(doctorName);
		List<DoctorResponseDTO> doctorDtoList = new ArrayList<>();
		for (Doctor a : doctorList) {
			DoctorResponseDTO doctorDto = modelMapper.map(a, DoctorResponseDTO.class);
			doctorDtoList.add(doctorDto);
		}
		if (!(doctorDtoList.isEmpty())) {
			return new ResponseEntity<>(doctorDtoList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(doctorDtoList, HttpStatus.BAD_REQUEST);
		}
	}
}