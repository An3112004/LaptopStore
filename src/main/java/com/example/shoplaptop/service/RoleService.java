package com.example.shoplaptop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.shoplaptop.domain.Role;
import com.example.shoplaptop.repository.RoleRepository;

@Service
public class RoleService {
    
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    
    public List<Role> findAll() {
        return this.roleRepository.findAll();
    }
}
