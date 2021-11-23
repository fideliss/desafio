package com.br.desafio.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProjetoDTO extends BaseDTO {

    private String nome;

    private DepartamentoDTO departamento;

}
