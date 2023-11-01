package com.g6.onlineeyecare.adminservicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.g6.onlineeyecare.admin.dao.IAdminRepository;
import com.g6.onlineeyecare.admin.dto.Admin;
import com.g6.onlineeyecare.admin.service.AdminServiceImpl;
import com.g6.onlineeyecare.exceptions.AdminIdNotFoundException;


@SpringBootTest
public class AdminServiceTest {
	IAdminRepository repository;

    @InjectMocks
    static AdminServiceImpl adminService;
    private static AutoCloseable ac;

    @Before
    public void doinit() {
        repository = mock(IAdminRepository.class);
        adminService = new AdminServiceImpl(repository);

        ac = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void doAtEnd() throws Exception {
        ac.close();
    }
    
    @Test
    @DisplayName("test -> add admin")
    public void addAdmin() {
        
        Admin expectedAdmin=new Admin("ram@gmail.com", "8456812954");
        expectedAdmin.setUserId(1);
        
        when(repository.save(expectedAdmin)).thenReturn(expectedAdmin);
        Admin actualAdmin=adminService.addAdmin(expectedAdmin);
        verify(repository).save(expectedAdmin);
        assertEquals(expectedAdmin, actualAdmin);
    }


    @Test
    @DisplayName("test -> delete admin by Id")
    public void deleteAdmin() throws AdminIdNotFoundException {
        
        Admin expectedAdmin=new Admin("ram@gmail.com", "8456812954");
        expectedAdmin.setUserId(1);
        
        when(repository.findById(1)).thenReturn(Optional.of(expectedAdmin));
        Admin actualAdmin=adminService.deleteAdmin(1); 
        verify(repository).deleteById(1);
        assertEquals(expectedAdmin, actualAdmin);
    }

    @Test
    @DisplayName("test -> delete admin by Id with invalid entries")
    public void deleteAdminInvalid() throws AdminIdNotFoundException  {
        Admin expectedAdmin=new Admin("ram@gmail.com", "8456812954");
        expectedAdmin.setUserId(1);
        
        when(repository.findById(1)).thenReturn(Optional.of(expectedAdmin));

        Executable executable = () -> adminService.deleteAdmin(10);
        assertThrows(AdminIdNotFoundException.class, executable);
            
    }
    
    @Test
    @DisplayName("test -> update admin with valid entries")
    public void updateAdmin() throws AdminIdNotFoundException
    {
        Admin expectedAdmin=new Admin("ram@gmail.com", "8456812954");
        expectedAdmin.setUserId(1);
        
        when(repository.findById(1)).thenReturn(Optional.of(expectedAdmin));
        Admin actualAdmin=adminService.updateAdmin(expectedAdmin); 
        verify(repository).save(expectedAdmin);
        assertEquals(expectedAdmin, actualAdmin);
    }
    
    @Test
    @DisplayName("test -> update admin with invalid entries")
    public void updateAdminInvalid() throws AdminIdNotFoundException
    {
        Admin expectedAdmin=new Admin("ram@gmail.com", "8456812954");
        expectedAdmin.setUserId(1);
        
        when(repository.findById(3)).thenReturn(Optional.of(expectedAdmin));
        Executable executable = () -> adminService.updateAdmin(expectedAdmin); 
        assertThrows(AdminIdNotFoundException.class, executable);
        
        
    }
    

}
