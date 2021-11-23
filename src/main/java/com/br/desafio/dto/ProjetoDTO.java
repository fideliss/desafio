package com.br.desafio.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProjetoDTO extends BaseDTO {

    private String nome;

    private DepartamentoDTO departamento;

    private Set<FuncionarioDTO> funcionarios;

    private BigDecimal valor;

    private LocalDate dataInicio;

    private LocalDate dataFim;

}
