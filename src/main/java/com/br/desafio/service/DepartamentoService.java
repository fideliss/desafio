package com.br.desafio.service;

import com.br.desafio.domain.Departamento;
import com.br.desafio.dto.DepartamentoDTO;
import com.br.desafio.mapper.DepartamentoMapper;
import com.br.desafio.repository.DepartamentoRepository;
import org.springframework.stereotype.Service;

@Service
public class DepartamentoService {

    private final DepartamentoRepository repository;

    private final DepartamentoMapper mapper;

    public DepartamentoService(DepartamentoRepository repository, DepartamentoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Departamento criar(DepartamentoDTO departamentoDto) {
        Departamento departamento = mapper.toEntity(departamentoDto);
        return departamento;
    }

    public void atualizar() {}

    public void listar() {}

    public void remover() {}

}
