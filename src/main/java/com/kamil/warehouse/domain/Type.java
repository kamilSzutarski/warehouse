package com.kamil.warehouse.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Type {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    @NotNull(message = "initials are required")
    private String initials;

    @Column(unique = true)
    @NotNull(message = "name is required")
    private String name;

    @CreatedDate
    private LocalDateTime createdDate;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String lastModifiedBy;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    //@JsonManagedReference
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "type")
    //jak robie w jednej w klasie OneToMany a w drugiej ManyToOne
    // to OneToMany musi miec mappedBy
    //private List<Product> productList;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "type")
    private List<Warehouse> warehouseList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "type")
    private List<Operator> operatorList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "type")
    private List<DocumentHeader> documentHeaderList;
}
