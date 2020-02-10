package com.kamil.warehouse.service.impl;

import com.kamil.warehouse.domain.Operator;
import com.kamil.warehouse.repository.OperatorRepository;
import com.kamil.warehouse.repository.RoleRepository;
import com.kamil.warehouse.repository.TypeRepository;
import com.kamil.warehouse.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;

@Service
public class OperatorServiceImpl implements OperatorService {

    @Autowired
    private OperatorRepository operatorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Override
    public Operator save(Operator operator, String... roleList) {
        operator.setPassword(passwordEncoder.encode(operator.getPassword())); //enkodujemy password
        // if present jest tylko na optionalu. /// ifPresent(role->operator.setRoles(Collections.singleton(role))); // jesli znajdzie w bazie danych role to przypisuje ja operatorowi
        operator.setRoles(roleRepository.findByNameIn(Arrays.asList(roleList)));
        operator.setType(typeRepository.findByName(operator.getType().getName()));
        return operatorRepository.save(operator);
    }

    @Override
    public Operator getById(Long id) {
        return operatorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product with id: " + id + " is not found"));
    }

    @Override
    public Page<Operator> getPage(Pageable pageable) {
        return operatorRepository.findAll(pageable);
    }

}
