package com.kamil.warehouse.service.impl;

import com.kamil.warehouse.domain.Role;
import com.kamil.warehouse.repository.RoleRepository;
import com.kamil.warehouse.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }
}
