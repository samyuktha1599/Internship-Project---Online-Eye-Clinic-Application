package com.g6.onlineeyecare.userservicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.g6.onlineeyecare.exceptions.AdminIdNotFoundException;
import com.g6.onlineeyecare.user.dao.IUserRepository;
import com.g6.onlineeyecare.user.dto.User;
import com.g6.onlineeyecare.user.service.UserServiceImpl;

@SpringBootTest
public class UserServiceTest {

	IUserRepository repository;

	@InjectMocks
	static UserServiceImpl userService;
	private static AutoCloseable ac;

	@Before
	public void doinit() {
		repository = mock(IUserRepository.class);
		userService = new UserServiceImpl(repository);

		ac = MockitoAnnotations.openMocks(this);
	}

	@AfterEach
	public void doAtEnd() throws Exception {
		ac.close();
	}

	

	@Test
	@DisplayName("test -> view user by Id")
	public void viewUserById() throws AdminIdNotFoundException {

		User u = new User(1, "abc", "Charlie", "doctor");

		when(repository.findById(1)).thenReturn(Optional.of(u));
		User actualUser = userService.viewUser(1);
		assertEquals(u, actualUser);
		verify(repository).findById(1);
	}
	
	@Test
	@DisplayName("test -> view user by Id with invalid entries")
	public void viewUserByIdInvalid() throws AdminIdNotFoundException {

		User u = new User(1, "abc", "Charlie", "doctor");

		when(repository.findById(1)).thenReturn(Optional.of(u));
		Executable executable = () -> userService.viewUser(2);
		assertThrows(AdminIdNotFoundException.class, executable);
	}

	@Test
	@DisplayName("test -> view all users")
	public void viewAllUsers() {

		User u = new User(1, "abc", "Charlie", "doctor");
		User u1 = new User(2, "xyz", "Jhonny", "patient");

		List<User> list = Arrays.asList(u, u1);

		when(repository.findAll()).thenReturn(list);
		List<User> actualList = userService.viewUsers();
		verify(repository).findAll();
		assertEquals(list, actualList);
	}

}
