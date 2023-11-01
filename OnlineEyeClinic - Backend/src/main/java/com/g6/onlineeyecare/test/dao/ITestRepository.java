package com.g6.onlineeyecare.test.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.g6.onlineeyecare.test.dto.Test;

@Repository
public interface ITestRepository extends JpaRepository<Test, Integer>{
	
}
