package com.g6.onlineeyecare.admin.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.g6.onlineeyecare.admin.dao.IAdminRepository;
import com.g6.onlineeyecare.admin.dto.Admin;
import com.g6.onlineeyecare.exceptions.AdminIdNotFoundException;

@Service
public class AdminServiceImpl implements IAdminService{

    @Autowired
    IAdminRepository repository;


    Logger log = LoggerFactory.getLogger(AdminServiceImpl.class);



    public AdminServiceImpl(IAdminRepository repository) {
        super();
        this.repository = repository;
    }

    @Override
    @Transactional
    public Admin addAdmin(Admin admin){
        try {
        	Optional<Admin> adminCheck = repository.findByuserName(admin.getUserName());
        	if(adminCheck.isPresent())
        	{
        		throw new Exception("Enter Unique UserName");
        	}
        	else
        	{
        		repository.save(admin);
        	}  
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return admin;
    }

    @Override
    @Transactional
    public Admin updateAdmin(Admin admin) throws AdminIdNotFoundException {
        Optional<Admin> optional=repository.findById(admin.getUserId());
        if(optional.isPresent())
        {
            repository.save(admin);
        }
        else {
            throw new AdminIdNotFoundException("Admin Id not found for updation");
        }
        return optional.get();
    }


    @Override
    @Transactional
    public Admin deleteAdmin(int adminId) throws AdminIdNotFoundException {

        Optional<Admin> optional = repository.findById(adminId);
        if (optional.isPresent()) {
            repository.deleteById(adminId);
        } else {
            throw new AdminIdNotFoundException("Admin Id not found for deletion");
        }
        return optional.get();
    }




}
