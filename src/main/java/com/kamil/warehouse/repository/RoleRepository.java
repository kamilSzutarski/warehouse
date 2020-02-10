package com.kamil.warehouse.repository;

import com.kamil.warehouse.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);

    Set<Role> findByNameIn(List<String> roleList); // select * from role where name in (role1, role2)


}