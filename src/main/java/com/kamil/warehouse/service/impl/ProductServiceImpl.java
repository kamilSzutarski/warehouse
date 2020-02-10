package com.kamil.warehouse.service.impl;

import com.kamil.warehouse.domain.Product;
import com.kamil.warehouse.repository.ProductRepository;
import com.kamil.warehouse.repository.TypeRepository;
import com.kamil.warehouse.repository.WarehouseRepository;
import com.kamil.warehouse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Override
    public Product save(Product product) {
        product.setType(typeRepository.findByName("Towar"));
        warehouseRepository.findById(product.getDefaultWarehouse().getId())
                .ifPresent(x -> product.setDefaultWarehouse(x));
        return productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        //findById jest wbudowana metoda z CRUDRepository. Albo znajdzie nic albo product. jedna z tych 2 wartosci wstawia do klasy optional
        //orElseThrow - funkcja optionala. Funkcja findById zwraca optionala
        return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product with id: " + id + " is not found"));
        //klasa optional to taka klasa ktora opakowuje obiekty i dzieki niej nie jestesmy narazeni na wystepowanie nullpointerexception
    }

    @Override
    public Page<Product> getPage(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public List<Product> findByNameContaining(String name) {
        return productRepository.findByNameContaining(name);
    }

    @Override
    public Page<Product> findByCodeContaining(String code, Pageable pageable) {
        return productRepository.findByCodeContaining(code, pageable);
    }

    @Override
    public Page<Product> findByDefaultWarehouseCode(String code, Pageable pageable) {
        return productRepository.findByDefaultWarehouseCode(code, pageable);
    }

    @Override
    public void removeProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product editProduct(Product product) {
        return productRepository.findById(product.getId()).map(prodDB -> {
            if (!prodDB.getCode().equals(product.getCode())) {
                prodDB.setCode(product.getCode());
            }
            return productRepository.save(prodDB);
        })
                .orElseThrow(() -> new EntityNotFoundException("Product with id doesn't exist"));
    }
}
