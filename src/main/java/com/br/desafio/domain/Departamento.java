package com.br.desafio.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "departamento")
public class Departamento extends BaseEntity {

    @Column
    private String nome;

    @Column
    private Long numero;

}
