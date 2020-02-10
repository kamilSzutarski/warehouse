package com.kamil.warehouse.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Role {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "Role name is required")
    @Column(unique = true)
    private String name;
}
