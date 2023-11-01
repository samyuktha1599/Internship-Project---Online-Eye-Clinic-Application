package com.g6.onlineeyecare.admin.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.g6.onlineeyecare.admin.dto.Admin;
import com.g6.onlineeyecare.admin.dto.AdminDTO;
import com.g6.onlineeyecare.admin.dto.AdminResponseDTO;
import com.g6.onlineeyecare.admin.service.IAdminService;
import com.g6.onlineeyecare.exceptions.AdminIdNotFoundException;
import com.g6.onlineeyecare.user.dto.User;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Validated
@Api(value = "Admin Rest Controller")
@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = {"http://localhost:9999","http://localhost:4200"}, allowedHeaders = "*")
public class AdminController {
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    IAdminService service;
    
    
    @ApiOperation(value = "add a new Admin", response = Admin.class)
    @PostMapping("/add")
    public ResponseEntity<AdminResponseDTO> addUser(@RequestBody @Valid AdminDTO admin) {
        
        Admin actual = modelMapper.map(admin, Admin.class);
        AdminResponseDTO response = modelMapper.map(this.service.addAdmin(actual), AdminResponseDTO.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "update profile", response = Admin.class)
    @PutMapping("/update")
    public ResponseEntity<AdminResponseDTO> updateUser(@RequestBody @Valid AdminDTO admin) throws AdminIdNotFoundException {
        
        Admin actual = modelMapper.map(admin, Admin.class);
        AdminResponseDTO response = modelMapper.map(this.service.updateAdmin(actual), AdminResponseDTO.class);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    
    @ApiOperation(value = "delete admin", response = User.class)
    @DeleteMapping("/delete/{adminId}")
    public ResponseEntity<AdminResponseDTO> removeUser(@PathVariable("adminId") int adminId) throws AdminIdNotFoundException{
        
        AdminResponseDTO response = modelMapper.map(this.service.deleteAdmin(adminId), AdminResponseDTO.class);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

}