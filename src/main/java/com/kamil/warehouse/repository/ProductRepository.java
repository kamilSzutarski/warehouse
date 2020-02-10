package com.kamil.warehouse.repository;

import com.kamil.warehouse.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/* dzieki rozszerzeniu o jparep moge robic crud
to tez jest bean bo dziedziczy po JpaRepository
JpaRep ma batchowe usuwanie, flushowanie(zapisywanie zmian do bazy danych ktore sa w statusie pending)
PagingAndSortingRepository - umozliwia stronnicowanie i sortowanie zapytan, z tego dziedziczy JpaRepository, dziedziczy po CRUDRepository
ktore umozliwia zapisywanie, szukanie, usuwanie i zmiane. On dziedziczy po Repository ktore jest beanem i dzieki temu wszystkie klasy ktore
po nim dziedzicza tez sa beanem */

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByNameContaining(String name);

    Page<Product> findByCodeContaining(String code, Pageable pageable);

    Page<Product> findByDefaultWarehouseCode(String code, Pageable pageable);

    //Query robie tylko wtedy, kiedy musimy laczenia miedzy tabelkami zrobic inne niz zwyklego joina np Left outer join

}
