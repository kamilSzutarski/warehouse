package com.kamil.warehouse.service;

import com.kamil.warehouse.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import java.util.List;

public interface ProductService {

    Product save(Product product);

    Product getById(Long id);

    Page<Product> getPage(@PageableDefault Pageable pageable);

    // jesli obiekt ktory jest w paramentrze jest nullem, to zostaje zwrocona 1 strona z bazy danych o wielkosci 10
    List<Product> findByNameContaining(String name); //w serwisie mowimy czy znalezlismy optionala, jesli tak to wyjatek

    Page<Product> findByCodeContaining(String code, Pageable pageable);

    Page<Product> findByDefaultWarehouseCode(String code, Pageable pageable);

    void removeProduct(Long id);

    Product editProduct(Product product);
}
