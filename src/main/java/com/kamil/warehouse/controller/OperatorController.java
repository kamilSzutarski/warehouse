package com.kamil.warehouse.controller;

import com.kamil.warehouse.domain.Operator;
import com.kamil.warehouse.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid; //validuje request

@RestController
@RequestMapping("/api/operator")

public class OperatorController {

    @Autowired
    private OperatorService operatorService;

    @PostMapping
    public Operator saveOperator(@RequestBody @Valid Operator operator, @RequestParam String... roleList) {
        return operatorService.save(operator, roleList);
    }

    @GetMapping("/{id}")
    public Operator getOperator(@PathVariable Long id) {
        return operatorService.getById(id);
    }

    @GetMapping
    public Page<Operator> operatorPage(@RequestParam Integer page, @RequestParam Integer size) {
        return operatorService.getPage(PageRequest.of(page, size));
    }
}
