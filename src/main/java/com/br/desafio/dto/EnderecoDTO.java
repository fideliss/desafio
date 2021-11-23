package com.br.desafio.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EnderecoDTO extends BaseDTO {

    private String pais;

    private String uf;

    private String cidade;

    private String rua;

    private String cep;

}
