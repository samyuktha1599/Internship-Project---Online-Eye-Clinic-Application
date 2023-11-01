package com.g6.onlineeyecare.appointment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
import com.g6.onlineeyecare.appointment.dto.Appointment;
import com.g6.onlineeyecare.appointment.dto.AppointmentDTO;
import com.g6.onlineeyecare.appointment.dto.AppointmentResponseDTO;
import com.g6.onlineeyecare.appointment.service.IAppointmentService;
import com.g6.onlineeyecare.exceptions.AppointmentIdNotFoundException;
import com.g6.onlineeyecare.exceptions.DoctorIdNotFoundException;
import com.g6.onlineeyecare.exceptions.InvalidAppointmentException;
import com.g6.onlineeyecare.exceptions.PatientIdFoundNotException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Validated
@Api(value = "Appointment Rest Controller")
@RestController
@RequestMapping("/appointment")
@CrossOrigin(origins = {"http://localhost:9999","http://localhost:4200"}, allowedHeaders = "*")
public class AppointmentController {
	
	@Autowired
	private ModelMapper modelMapper;
	

	@Autowired
	IAppointmentService appointmentService;
	
	@ApiOperation(value = "Book appointment to consult the doctor",response = Appointment.class)
	@PostMapping("/book")
	public  ResponseEntity<AppointmentResponseDTO> bookAppointment(@RequestBody @Valid AppointmentDTO appointment) throws DoctorIdNotFoundException, PatientIdFoundNotException 
	{
		Appointment actual = modelMapper.map(appointment, Appointment.class);
		AppointmentResponseDTO response = modelMapper.map(this.appointmentService.bookAppointment(actual),AppointmentResponseDTO.class);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Update your appointment ",response = Appointment.class)
	@PutMapping("/update")
	public ResponseEntity<AppointmentResponseDTO> updateAppointment(@RequestBody AppointmentDTO appointment) throws InvalidAppointmentException
	{
		Appointment actual = modelMapper.map(appointment, Appointment.class);
		AppointmentResponseDTO response = modelMapper.map(this.appointmentService.updateAppointment(actual), AppointmentResponseDTO.class);
		if (response != null) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Cancel by Id ",response = Appointment.class)
	@DeleteMapping("/cancel/{appointmentId}")
	public ResponseEntity<AppointmentResponseDTO> cancelAppointment(@PathVariable("appointmentId") int appointmentId) throws AppointmentIdNotFoundException 
	{
		AppointmentResponseDTO response = modelMapper.map(this.appointmentService.cancelAppointment(appointmentId), AppointmentResponseDTO.class);
		if (response != null) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "Get the required appointment by Id ",response = Appointment.class)
	@GetMapping("/view/{appointmentId}")
	public ResponseEntity<AppointmentResponseDTO> viewAppointment(@PathVariable("appointmentId") int appointmentId) throws AppointmentIdNotFoundException
	{
		AppointmentResponseDTO response = modelMapper.map(this.appointmentService.viewAppointment(appointmentId), AppointmentResponseDTO.class);
		if (response != null) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Get the list of all appointments ",response = Appointment.class)
	@GetMapping("/viewAll")
	public ResponseEntity<List<AppointmentResponseDTO>> viewAllAppointments()
	{
		List<Appointment> appointmentList = this.appointmentService.viewAllAppointments();
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
	
	@ApiOperation(value = "Get the required appointment by date ",response = Appointment.class)
	@GetMapping("/viewByDate/{date}")
	public ResponseEntity<List<AppointmentResponseDTO>> viewAppointments(@PathVariable("date")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date)
	{
		List<Appointment> appointmentList = this.appointmentService.viewAppointments(date);
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
	
	@ApiOperation(value = "Get the required appointment by date and doctor name ",response = Appointment.class)
    @GetMapping("/viewByName/{date}/{doctorName}")
    public ResponseEntity<List<AppointmentResponseDTO>> viewAppointmentByDateAndName(@PathVariable("date")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,@PathVariable("doctorName") String doctorName)
    {
        List<Appointment> appointmentList = this.appointmentService.viewAppointmentByDateAndName(date, doctorName);
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
	
}