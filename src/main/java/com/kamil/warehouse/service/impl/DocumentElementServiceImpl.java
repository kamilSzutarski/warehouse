package com.kamil.warehouse.service.impl;

import com.kamil.warehouse.domain.DocumentElement;
import com.kamil.warehouse.repository.DocumentElementRepository;
import com.kamil.warehouse.service.DocumentElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class DocumentElementServiceImpl implements DocumentElementService {

    @Autowired
    DocumentElementRepository documentElementRepository;

    @Override
    public DocumentElement save(DocumentElement documentElement) {
        return documentElementRepository.save(documentElement);
    }

    @Override
    public DocumentElement getById(Long id) {
        return documentElementRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("DocumentElement with id: " + id + " is not found"));
    }

    @Override
    public Page<DocumentElement> getPage(Pageable pageable) {
        return documentElementRepository.findAll(pageable);
    }
}
