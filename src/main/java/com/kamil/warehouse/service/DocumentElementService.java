package com.kamil.warehouse.service;

import com.kamil.warehouse.domain.DocumentElement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

public interface DocumentElementService {

    DocumentElement save(DocumentElement documentElement);

    DocumentElement getById(Long id);

    Page<DocumentElement> getPage(@PageableDefault Pageable pageable);
}
