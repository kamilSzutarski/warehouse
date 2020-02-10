package com.kamil.warehouse.service.impl;

import com.kamil.warehouse.domain.Warehouse;
import com.kamil.warehouse.repository.WarehouseRepository;
import com.kamil.warehouse.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Override
    public Warehouse save(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    @Override
    public Warehouse getById(Long id) {
        return warehouseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Warehouse with id: " + id + " is not found"));
    }

    @Override
    public Page<Warehouse> getPage(Pageable pageable) {
        return warehouseRepository.findAll(pageable);
    }

    @Override
    public List<Warehouse> findByNameContaining(String name) {
        return warehouseRepository.findByNameContaining(name);
    }

    @Override
    public List<Warehouse> findByCodeContaining(String code) {
        return warehouseRepository.findByCodeContaining(code);
    }

    @Override
    public List<Warehouse> findAllWarehouse() {
        return warehouseRepository.findAll();
    }
}
