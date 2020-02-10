package com.kamil.warehouse.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)

public class DocumentHeader {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "series are required")
    private String series;

    @CreatedDate
    private LocalDateTime createdDate;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @LastModifiedBy
    private String lastModifiedBy;

    @NotNull(message = "number is required")
    private Integer number;

    @NotNull(message = "year is required")
    private Integer year;

    @NotNull(message = "month is required")
    private Integer month;

    @NotNull(message = "weight is required")
    private Double weight;

    @NotNull(message = "amount is required")
    private Double amount;

    /* fetch ma 2 wartosci: lazy i eager: lazy podczas pobierania klasy nie zostanie pobrany element list, dopiero po wywolaniu getteru
    eager zawsze pobiera liste
    operacje na elementach wszystkich jest mozliwe dzieki kaskadzie
    orphan usuwa obiekty powiazane z warehouseheader */

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "documentHeader")
    private List<DocumentElement> elementList = new LinkedList<>();

    @NotNull(message = "type is required")
    @ManyToOne(cascade = CascadeType.ALL)
    private Type type;

    @NotNull(message = "source is required")
    @ManyToOne(cascade = CascadeType.ALL)
    private Warehouse sourceWh;

    @NotNull(message = "target is required")
    @ManyToOne(cascade = CascadeType.ALL)
    private Warehouse targetWh;

    @ManyToOne
    private Operator operator;

}
