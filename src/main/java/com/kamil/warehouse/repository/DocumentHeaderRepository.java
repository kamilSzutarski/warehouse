package com.kamil.warehouse.repository;

import com.kamil.warehouse.domain.DocumentHeader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentHeaderRepository extends JpaRepository<DocumentHeader, Long> {

    List<DocumentHeader> findBySeriesContaining(String series);

    List<DocumentHeader> findByNumberContaining(Integer number);

    List<DocumentHeader> findByMonthContaining(Integer month);

    List<DocumentHeader> findByYearContaining(Integer year);

    List<DocumentHeader> findByAmountContaining(Double amount);

    List<DocumentHeader> findByOperatorContaining(String ident);

    List<DocumentHeader> findBySourceWhContaining(String code);

    List<DocumentHeader> findByTargetWhContaining(String code);

}
