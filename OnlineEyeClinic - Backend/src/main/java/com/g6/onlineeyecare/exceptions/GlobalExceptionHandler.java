package com.g6.onlineeyecare.exceptions;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler
	public ResponseEntity<MyExceptionResponse> invalidAppointmentIdException(AppointmentIdNotFoundException ex)
	{
		String message=ex.getMessage();
		MyExceptionResponse response=new MyExceptionResponse();
		response.setHttpStatus(HttpStatus.NOT_FOUND.value());
		response.setExceptionMsg(message);
		
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	

	@ExceptionHandler
	public ResponseEntity<MyExceptionResponse> invalidDoctorIdException(DoctorIdNotFoundException ex)
	{
		String message=ex.getMessage();
		MyExceptionResponse response=new MyExceptionResponse();
		response.setHttpStatus(HttpStatus.NOT_FOUND.value());
		response.setExceptionMsg(message);
		
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	

	@ExceptionHandler
	public ResponseEntity<MyExceptionResponse> invalidAppointmentException(InvalidAppointmentException ex)
	{
		String message=ex.getMessage();
		MyExceptionResponse response=new MyExceptionResponse();
		response.setHttpStatus(HttpStatus.NOT_FOUND.value());
		response.setExceptionMsg(message);
		
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<MyExceptionResponse> invalidPatientIdException(PatientIdFoundNotException ex)
	{
		String message=ex.getMessage();
		MyExceptionResponse response=new MyExceptionResponse();
		response.setHttpStatus(HttpStatus.NOT_FOUND.value());
		response.setExceptionMsg(message);
		
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<MyExceptionResponse> invalidReportIdException(ReportIdNotFoundException ex)
	{
		String message=ex.getMessage();
		MyExceptionResponse response=new MyExceptionResponse();
		response.setHttpStatus(HttpStatus.NOT_FOUND.value());
		response.setExceptionMsg(message);
		
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<MyExceptionResponse> invalidSpectaclesIdException(SpectaclesIdNotFoundException ex)
	{
		String message=ex.getMessage();
		MyExceptionResponse response=new MyExceptionResponse();
		response.setHttpStatus(HttpStatus.NOT_FOUND.value());
		response.setExceptionMsg(message);
		
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<MyExceptionResponse> invalidTestIdException(TestIdNotFoundException ex)
	{
		String message=ex.getMessage();
		MyExceptionResponse response=new MyExceptionResponse();
		response.setHttpStatus(HttpStatus.NOT_FOUND.value());
		response.setExceptionMsg(message);
		
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}

	
	@ExceptionHandler
	public ResponseEntity<MyExceptionResponse> invalidUserIdException(AdminIdNotFoundException ex)
	{
		String message=ex.getMessage();
		MyExceptionResponse response=new MyExceptionResponse();
		response.setHttpStatus(HttpStatus.NOT_FOUND.value());
		response.setExceptionMsg(message);
		
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<MyExceptionResponse> handleInvalidCredentialException(InvalidCredentialException ex)
	{
		String message=ex.getMessage();
		MyExceptionResponse response=new MyExceptionResponse();
		response.setHttpStatus(HttpStatus.NOT_FOUND.value());
		response.setExceptionMsg(message);
		
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, String> map = new HashMap<>();

		ex.getBindingResult().getAllErrors().forEach( error -> {
			String fieldName = ((FieldError) error).getField();
			String msg = error.getDefaultMessage();

			map.put(fieldName, msg);
		});
		return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		return new ResponseEntity<>("Date format Exception ->" + " Expected Date format: yyyy-MM-dd",
				HttpStatus.BAD_REQUEST);
	}

}
