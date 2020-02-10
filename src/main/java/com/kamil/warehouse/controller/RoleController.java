package com.kamil.warehouse.controller;

import com.kamil.warehouse.domain.Role;
import com.kamil.warehouse.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    public List<Role> getRoles(){
        return roleService.getRoles();
    }

}
