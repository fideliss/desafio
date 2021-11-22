package com.br.desafio.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class BaseDTO implements Serializable {

    private Long id;

    private UUID uuid;

}
