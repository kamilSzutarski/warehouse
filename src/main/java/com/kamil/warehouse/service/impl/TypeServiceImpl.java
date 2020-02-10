package com.kamil.warehouse.service.impl;

import com.kamil.warehouse.domain.Type;
import com.kamil.warehouse.repository.TypeRepository;
import com.kamil.warehouse.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    TypeRepository typeRepository;

    @Override
    public Type save(Type type) {
        return typeRepository.save(type);
    }

    @Override
    public Type getById(Long id) {
        return typeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Type with id: " + id + " is not found"));
    }

    @Override
    public Page<Type> getPage(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Override
    public List<Type> findByNameContaining(String name) {
        return typeRepository.findByNameContaining(name);
    }

    @Override
    public List<Type> findByInitialsContaining(String initials) {
        return typeRepository.findByInitialsContaining(initials);
    }
}
