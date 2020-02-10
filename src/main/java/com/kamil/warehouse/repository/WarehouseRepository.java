package com.kamil.warehouse.repository;

import com.kamil.warehouse.domain.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    List<Warehouse> findByNameContaining(String name);

    List<Warehouse> findByCodeContaining(String code);
}
