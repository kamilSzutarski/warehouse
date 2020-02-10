package com.kamil.warehouse.controller;

import com.kamil.warehouse.domain.Warehouse;
import com.kamil.warehouse.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouse")

public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @PostMapping
    public Warehouse saveWarehouse(@RequestBody Warehouse warehouse) {
        return warehouseService.save(warehouse);
    }

    @GetMapping("/{id}")
    public Warehouse getWarehouse(@PathVariable Long id) {
        return warehouseService.getById(id);
    }

    @GetMapping
    public Page<Warehouse> getPage(@RequestParam Integer page, @RequestParam Integer size) {
        return warehouseService.getPage(PageRequest.of(page, size));
    }

    @GetMapping("/name/{name}")
    public List<Warehouse> findByName(@PathVariable String name){
        return warehouseService.findByNameContaining(name);
    }

    @GetMapping("/code/{code}")
    public List<Warehouse> findByCode(@PathVariable String code){
        return warehouseService.findByCodeContaining(code);
    }

    @GetMapping("/all")
    public List<Warehouse> findAllWarehouse(){
        return warehouseService.findAllWarehouse();
    }
}
