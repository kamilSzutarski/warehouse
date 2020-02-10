package com.kamil.warehouse.service;

import com.kamil.warehouse.domain.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import java.util.List;

public interface TypeService {

    Type save(Type type);

    Type getById(Long id);

    Page<Type> getPage(@PageableDefault Pageable pageable);

    List<Type> findByNameContaining(String name);

    List<Type> findByInitialsContaining(String initials);

}
