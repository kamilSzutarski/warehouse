package com.kamil.warehouse.domain;

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

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)

//POJO - old java object
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "product code is required")
    @Column(unique = true)
    private String code;

    @NotNull(message = "product name is required")
    @Column(unique = true)
    private String name;

    @NotNull(message = "product price is required")
    private Double price;

    @NotNull(message = "product weight is required")
    private Double weight;

    @NotNull
    private Double quantity;

    @NotNull(message = "product unit is required")
    private Integer unit;

    @ManyToOne
    @JoinColumn(name = "default_warehouse_id")
    private Warehouse defaultWarehouse;

    @CreatedDate
    private LocalDateTime createdDate;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @LastModifiedBy
    private String lastMidifiedBy;

    @ManyToOne(cascade = CascadeType.ALL)
    private Type type;
}
