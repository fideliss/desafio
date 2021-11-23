package com.br.desafio.mapper;

import com.br.desafio.domain.Projeto;
import com.br.desafio.dto.ProjetoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { DepartamentoMapper.class, FuncionarioMapper.class })
public interface ProjetoMapper extends EntityMapper<ProjetoDTO, Projeto> {

}
