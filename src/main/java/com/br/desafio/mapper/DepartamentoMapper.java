package com.br.desafio.mapper;

import com.br.desafio.domain.Departamento;
import com.br.desafio.dto.DepartamentoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartamentoMapper extends EntityMapper<DepartamentoDTO, Departamento> {

}
