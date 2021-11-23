package com.br.desafio.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "projeto")
public class Projeto extends BaseEntity {

    @Column
    private String nome;

    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

    @Column
    private BigDecimal valor;

    @Column
    private LocalDate dataInicio;

    @Column
    private LocalDate dataFim;

    @ManyToMany
    @JoinTable(
            name = "funcionario_projeto",
            joinColumns = @JoinColumn(name = "projeto_id"),
            inverseJoinColumns = @JoinColumn(name = "funcionario_id"))
    private Set<Funcionario> funcionarios;

}
