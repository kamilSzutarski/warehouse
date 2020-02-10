package com.kamil.warehouse.service;

import com.kamil.warehouse.domain.DocumentHeader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import java.util.List;

public interface DocumentHeaderService {

    DocumentHeader save(DocumentHeader documentHeader);

    DocumentHeader getById(Long id);

    Page<DocumentHeader> getPage(@PageableDefault Pageable pageable);

    List<DocumentHeader> findBySeriesContaining(String series);

    List<DocumentHeader> findByNumberContaining(Integer number);

    List<DocumentHeader> findByMonthContaining(Integer month);

    List<DocumentHeader> findByYearContaining(Integer year);

    List<DocumentHeader> findByAmountContaining(Double amount);

    List<DocumentHeader> findByOperatorContaining(String ident);

    List<DocumentHeader> findBySourceWhContaining(String code);

    List<DocumentHeader> findByTargetWhContaining(String code);
}
