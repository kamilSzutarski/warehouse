package com.kamil.warehouse.service.impl;

import com.kamil.warehouse.domain.DocumentHeader;
import com.kamil.warehouse.repository.DocumentHeaderRepository;
import com.kamil.warehouse.service.DocumentHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class DocumentHeaderServiceImpl implements DocumentHeaderService {

    @Autowired
    DocumentHeaderRepository documentHeaderRepository;

    @Override
    public DocumentHeader save(DocumentHeader documentHeader) {
        return documentHeaderRepository.save(documentHeader);
    }

    @Override
    public DocumentHeader getById(Long id) {
        return documentHeaderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("DocumentHeader with id: " + id + " is not found"));
    }

    @Override
    public Page<DocumentHeader> getPage(Pageable pageable) {
        return documentHeaderRepository.findAll(pageable);
    }

    @Override
    public List<DocumentHeader> findBySeriesContaining(String series) {
        return documentHeaderRepository.findBySeriesContaining(series);
    }

    @Override
    public List<DocumentHeader> findByNumberContaining(Integer number) {
        return documentHeaderRepository.findByNumberContaining(number);
    }

    @Override
    public List<DocumentHeader> findByMonthContaining(Integer month) {
        return documentHeaderRepository.findByMonthContaining(month);
    }

    @Override
    public List<DocumentHeader> findByYearContaining(Integer year) {
        return documentHeaderRepository.findByYearContaining(year);
    }

    @Override
    public List<DocumentHeader> findByAmountContaining(Double amount) {
        return documentHeaderRepository.findByAmountContaining(amount);
    }

    @Override
    public List<DocumentHeader> findByOperatorContaining(String ident) {
        return documentHeaderRepository.findByOperatorContaining(ident);
    }

    @Override
    public List<DocumentHeader> findBySourceWhContaining(String code) {
        return documentHeaderRepository.findBySourceWhContaining(code);
    }

    @Override
    public List<DocumentHeader> findByTargetWhContaining(String code) {
        return documentHeaderRepository.findByTargetWhContaining(code);
    }
}
