package com.kamil.warehouse.repository;

import com.kamil.warehouse.domain.Operator;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface OperatorRepository extends JpaRepository<Operator, Long> {

    Optional<Operator> findByIdent(String ident);

}
