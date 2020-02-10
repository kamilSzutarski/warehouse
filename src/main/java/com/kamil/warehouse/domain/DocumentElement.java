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

public class DocumentElement {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "quantity is required")
    private Integer quantity;

    //@ManyToOne(cascade = CascadeType.ALL) // produkt może istnieć w wielu elementach
    //private Product product;

    @ManyToOne(cascade = CascadeType.ALL) // ManyToOne to 1 obiekt, OneToMany to lista obiektów
    private DocumentHeader documentHeader;
}
