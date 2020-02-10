package com.kamil.warehouse.service;

import com.kamil.warehouse.domain.Operator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

public interface OperatorService {

    Operator save(Operator operator, String... roleList);

    Operator getById(Long id);

    Page<Operator> getPage(@PageableDefault Pageable pageable);

}
