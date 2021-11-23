package com.br.desafio.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "departamento")
public class Departamento extends BaseEntity {

    @Column
    private String nome;

    @Column
    private Long numero;

    @Column
    private BigDecimal orcamento;

    @Column
    private LocalDate dataInicio;

    @Column
    private LocalDate dataFim;

}
