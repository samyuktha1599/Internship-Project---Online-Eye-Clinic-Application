package com.g6.onlineeyecare.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.g6.onlineeyecare.exceptions.InvalidCredentialException;
import com.g6.onlineeyecare.security.JwtUtil;
import com.g6.onlineeyecare.exceptions.AdminIdNotFoundException;

import com.g6.onlineeyecare.user.dao.IUserRepository;
import com.g6.onlineeyecare.user.dto.User;

@Service
public class UserServiceImpl implements IUserService,UserDetailsService {

	@Autowired
	IUserRepository repository;
	
	@Autowired
	JwtUtil util;
	
	@Autowired
	AuthenticationManager authManager;

	Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	public UserServiceImpl(IUserRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public User viewUser(int userId) throws AdminIdNotFoundException {

		Optional<User> optional = repository.findById(userId);
		if (!optional.isPresent()) {
			throw new AdminIdNotFoundException("User Id not found to view user");
		}
		return optional.get();
	}

	@Override
	public List<User> viewUsers() {
		List<User> userList = null;
		try {
			userList = repository.findAll();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return userList;
	}
	
	@Override
	public String signIn(User user) throws InvalidCredentialException {
		try {
			this.authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
		} catch (Exception e) {
			throw new InvalidCredentialException("Invalid credentials");
		}
		UserDetails userDetail = this.loadUserByUsername(user.getUserName());
		String token = this.util.generateToken(userDetail);
		return token;
	}
	
	
	@Override
	public User signOut() {
		
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByUserName(username);
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), new ArrayList<>());
	}
	
}
