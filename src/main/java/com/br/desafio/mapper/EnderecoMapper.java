package com.br.desafio.mapper;

import com.br.desafio.domain.Endereco;
import com.br.desafio.dto.EnderecoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoMapper extends EntityMapper<EnderecoDTO, Endereco> {

}
