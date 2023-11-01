package com.g6.onlineeyecare.test.service;

import java.util.List;

import com.g6.onlineeyecare.exceptions.PatientIdFoundNotException;
import com.g6.onlineeyecare.exceptions.TestIdNotFoundException;
import com.g6.onlineeyecare.test.dto.Test;

public interface ITestService {
	Test addTest(Test test) throws PatientIdFoundNotException;

	Test updateTest(Test test) throws TestIdNotFoundException;

	Test removeTest(int testId) throws TestIdNotFoundException;

	Test viewTest(int testId) throws TestIdNotFoundException;

	List<Test> viewAllTests();
}
