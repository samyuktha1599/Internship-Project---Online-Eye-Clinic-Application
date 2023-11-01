package com.g6.onlineeyecare.test.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;

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

import com.g6.onlineeyecare.exceptions.PatientIdFoundNotException;
import com.g6.onlineeyecare.exceptions.TestIdNotFoundException;
import com.g6.onlineeyecare.test.dto.Test;
import com.g6.onlineeyecare.test.dto.TestDTO;
import com.g6.onlineeyecare.test.dto.TestResponseDTO;
import com.g6.onlineeyecare.test.service.ITestService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Validated
@Api(value = "Test Rest Controller")
@RestController
@RequestMapping("/test")
@CrossOrigin(origins = {"http://localhost:9999","http://localhost:4200"}, allowedHeaders = "*")
public class TestController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	ITestService testService;

	@ApiOperation(value = "Create a new test", response = Test.class)
	@PostMapping("/add")
	public ResponseEntity<TestResponseDTO> addTest(@RequestBody @Valid TestDTO test) throws PatientIdFoundNotException {

		Test actual = modelMapper.map(test, Test.class);
		TestResponseDTO response = modelMapper.map(this.testService.addTest(actual), TestResponseDTO.class);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@ApiOperation(value = "Update the specific test", response = Test.class)
	@PutMapping("/update")
	public ResponseEntity<TestResponseDTO> updateTest(@RequestBody TestDTO test) throws TestIdNotFoundException {

		Test actual = modelMapper.map(test, Test.class);
		TestResponseDTO response = modelMapper.map(this.testService.updateTest(actual), TestResponseDTO.class);
		if (response != null) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "Delete the specific test", response = Test.class)
	@DeleteMapping("/delete/{testId}")
	public ResponseEntity<TestResponseDTO> deleteTest(@PathVariable("testId") int testId)
			throws TestIdNotFoundException {

		TestResponseDTO response = modelMapper.map(this.testService.removeTest(testId), TestResponseDTO.class);
		if (response != null) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "View the test by Id", response = Test.class)
	@GetMapping("/view/{testId}")
	public ResponseEntity<TestResponseDTO> viewTest(@PathVariable("testId") int testId) throws TestIdNotFoundException {

		TestResponseDTO response = modelMapper.map(this.testService.viewTest(testId), TestResponseDTO.class);
		if (response != null) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

	}

	@ApiOperation(value = "View all Tests", response = Test.class)
	@GetMapping("/viewAll")
	public ResponseEntity<List<TestResponseDTO>> viewAllTests() {

		List<Test> testList = this.testService.viewAllTests();
		List<TestResponseDTO> testDtoList = new ArrayList<>();
		for (Test t : testList) {
			TestResponseDTO testDto = modelMapper.map(t, TestResponseDTO.class);
			testDtoList.add(testDto);
		}
		if (!(testDtoList.isEmpty())) {
			return new ResponseEntity<>(testDtoList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(testDtoList, HttpStatus.BAD_REQUEST);
		}
	}
}