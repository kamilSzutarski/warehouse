package com.kamil.warehouse.service;

import com.kamil.warehouse.domain.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import java.util.List;

public interface WarehouseService {

    Warehouse save(Warehouse warehouse);

    Warehouse getById(Long id);

    Page<Warehouse> getPage(@PageableDefault Pageable pageable);

    List<Warehouse> findByNameContaining(String name);

    List<Warehouse> findByCodeContaining(String code);

    List<Warehouse> findAllWarehouse();
}
