package com.br.desafio.mapper;

public interface EntityMapper<D, E> extends EntityToDtoMapper<D, E>, DtoToEntityMapper<D, E> {
}
