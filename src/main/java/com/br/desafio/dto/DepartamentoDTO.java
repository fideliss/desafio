package com.br.desafio.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
public class DepartamentoDTO extends BaseDTO {

    private String nome;

    private Long numero;

    private BigDecimal orcamento;

    private LocalDate dataInicio;

    private LocalDate dataFim;

}
