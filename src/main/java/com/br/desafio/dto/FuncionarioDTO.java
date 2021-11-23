package com.br.desafio.dto;

import com.br.desafio.enumeration.Sexo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class FuncionarioDTO extends BaseDTO {

    private String nome;

    private String cpf;

    private LocalDate dataNascimento;

    private Sexo sexo;

    private EnderecoDTO endereco;

    private FuncionarioDTO supervisor;

}
