package com.br.desafio.mapper;

import java.util.List;
import java.util.Set;

public interface EntityToDtoMapper<D, E> {

    D toDto(E entity);

    List<D> toDto(List<E> entityList);

    Set<D> toDto(Set<E> entitySet);

}
