package com.kamil.warehouse.controller;

import com.kamil.warehouse.domain.Type;
import com.kamil.warehouse.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/type")

public class TypeController {

    @Autowired
    private TypeService typeService;

    @PostMapping
    public Type saveType(@RequestBody Type type) {
        return typeService.save(type);
    }

    @GetMapping("/{id}")
    public Type getType(@PathVariable Long id) {
        return typeService.getById(id);
    }

    @GetMapping
    public Page<Type> getPage(@RequestParam Integer page, @RequestParam Integer size) {
        return typeService.getPage(PageRequest.of(page, size));
    }

    @GetMapping("/{initials")
    public List<Type> findByInitials(@PathVariable String initials){
        return typeService.findByInitialsContaining(initials);
    }

    @GetMapping("/{name}")
    public List<Type> findByName(@PathVariable String name){
        return typeService.findByNameContaining(name);
    }

}
