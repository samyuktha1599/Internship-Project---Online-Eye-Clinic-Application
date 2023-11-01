package com.g6.onlineeyecare.admin.service;


import com.g6.onlineeyecare.admin.dto.Admin;
import com.g6.onlineeyecare.exceptions.AdminIdNotFoundException;


public interface IAdminService {

    Admin addAdmin(Admin admin);

    Admin updateAdmin (Admin admin) throws  AdminIdNotFoundException;

    Admin deleteAdmin (int adminId) throws AdminIdNotFoundException;




}