package com.kamil.warehouse.repository;

import com.kamil.warehouse.domain.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TypeRepository extends JpaRepository<Type, Long> {

    List<Type> findByNameContaining(String name);

    List<Type> findByInitialsContaining(String initials);

    Type findByName(String name);
}
