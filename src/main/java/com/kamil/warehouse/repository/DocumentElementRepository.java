package com.kamil.warehouse.repository;

import com.kamil.warehouse.domain.DocumentElement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentElementRepository extends JpaRepository<DocumentElement, Long> {
}
