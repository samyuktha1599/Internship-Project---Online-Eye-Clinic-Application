package com.g6.onlineeyecare.user.dao;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.g6.onlineeyecare.user.dto.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer>{

	User findByUserName(String userName);
}
