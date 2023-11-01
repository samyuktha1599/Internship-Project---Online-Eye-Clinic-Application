package com.g6.onlineeyecare.user.service;

import java.util.List;

import com.g6.onlineeyecare.exceptions.InvalidCredentialException;
import com.g6.onlineeyecare.exceptions.AdminIdNotFoundException;
import com.g6.onlineeyecare.user.dto.User;

public interface IUserService {

	
	User viewUser(int userId) throws AdminIdNotFoundException;

	List<User> viewUsers();
	
	String signIn(User user) throws InvalidCredentialException;
	
	User signOut();

}
