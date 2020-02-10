package com.kamil.warehouse.controller;

import com.kamil.warehouse.domain.Product;
import com.kamil.warehouse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/* spring tworzy klase ProductServiceAdapter, ktora implementuje wszystkie metody z ProductService
w implemetacji metody jest wywolywana metoda z klasy ProductServiceImpl
przed wywolaniem i po wywolaniu tej metody generuje sie kod zwiazany z adnotacjami (jesli posiada) dla tej metody. */

@RestController
@RequestMapping("/api/product")

public class ProductController {

    /* wstrzykiwana jest implementacja ProductServiceAdapter a nie ProductServiceImpl
    wszystkie beany z defaultu sa singletonami. Jesli nie chcemy zeby byl singletonem to musimy powiedziec ze scope tego Beana jest prototypem.
    beanem jest klasa oznaczona adnotacja: restcontroller, controller, component, repository, bean, service */

    @Autowired
    private ProductService productService;

    @PostMapping
    public Product saveProduct(@RequestBody Product product) { // RequestBody - musi być Json, który będzie opisywał klasę Product
        return productService.save(product);
    }

    @GetMapping("/id/{id}")
    public Product getProduct(@PathVariable Long id) { // PathVariable - zmienna będzie pobierana z linku
        return productService.getById(id);
    }

    @GetMapping
    public Page<Product> productPage(@RequestParam Integer page, @RequestParam Integer size) {
        // jak daję RequestParam to w linku podaję ?page=0&size=10
        return productService.getPage(PageRequest.of(page, size));
    }

    @GetMapping("/name/{name}")
    public List<Product> findByNameContaining(@PathVariable String name) {
        return productService.findByNameContaining(name);
    }

    @GetMapping("/code/{code}/{page}/{size}")
    public Page<Product> findByCodeContaining(@PathVariable String code, @PathVariable Integer page, @PathVariable Integer size) {
        return productService.findByCodeContaining(code, PageRequest.of(page, size));
    }

    @GetMapping("/warehouse-code/{code}")
    public Page<Product> findByDefaultWarehouseCode(@PathVariable String code, @RequestParam Integer page, @RequestParam Integer size){
        return productService.findByDefaultWarehouseCode(code, PageRequest.of(page, size));
    }

    @DeleteMapping
    public void removeProduct(@RequestParam Long id){
        productService.removeProduct(id);
    }

    @PutMapping
    public Product editProduct(@RequestBody Product product){
        return productService.editProduct(product);
    }

}
