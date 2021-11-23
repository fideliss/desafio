package com.br.desafio.domain;

import com.br.desafio.enumeration.Sexo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "funcionario")
public class Funcionario extends BaseEntity {

    @Column
    private String nome;

    @Column
    private String cpf;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

    @OneToOne
    @JoinColumn(name = "supervisor_id", referencedColumnName = "id")
    private Funcionario supervisor;

}
