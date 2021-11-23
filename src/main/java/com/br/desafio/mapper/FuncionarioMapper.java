package com.br.desafio.mapper;

import com.br.desafio.domain.Funcionario;
import com.br.desafio.dto.FuncionarioDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { EnderecoMapper.class })
public interface FuncionarioMapper extends EntityMapper<FuncionarioDTO, Funcionario> {

}
