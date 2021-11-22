package com.br.desafio.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "endereco")
public class Endereco extends BaseEntity {

    @Column
    private String pais;

    @Column
    private String uf;

    @Column
    private String cidade;

    @Column
    private String rua;

    @Column
    private String cep;

}
