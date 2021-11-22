package com.br.desafio.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "projeto")
public class Projeto extends BaseEntity {

    @Column
    private String nome;

    @JoinColumn(name="departamento_id")
    private Departamento departamento;

}
