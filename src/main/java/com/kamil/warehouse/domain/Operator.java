package com.kamil.warehouse.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
//włącza listener dla wstawiania zmiennych oznaczonych adnotacjami createdDate, createdBy, lastModifiedDate, lastModifiedBy

public class Operator {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    @NotNull(message = "identyficator is required")
    @Length(min = 3, max = 5, message = "identyficator must have 3-5 characters")
    private String ident;

    @NotNull(message = "name is required")
    @Length(min = 2, max = 50, message = "name must have 2-50 characters")
    private String name;

    @NotNull(message = "password is required")
    @Length(min = 6, max = 70, message = "password must have 6-11 characters")
    private String password;

    @CreatedDate
    private LocalDateTime createdDate;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @LastModifiedBy
    private String lastModifiedBy;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "operator")
    private List<DocumentHeader> documentHeaders;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE}, fetch = FetchType.EAGER) // domyslnie jest lazy w ManyToMany
    // jakie operacje możemy wykonywać na wiązaniach, Detach znaczy, że jak usunę Operatora to nie usuwa wartości z tabeli Role
    @JoinTable // stwórz tabelke lączącą pomiędzy Operator a Role. ManyToMany musi mieć tabelke lączącą
    private Set<Role> roles;

    @ManyToOne(cascade = CascadeType.ALL)
    private Type type;

}
