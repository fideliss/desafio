package com.br.desafio.service;

import com.br.desafio.repository.DepartamentoRepository;
import org.springframework.stereotype.Service;

@Service
public class DepartamentoService {

    private final DepartamentoRepository repository;

    public DepartamentoService(DepartamentoRepository repository) {
        this.repository = repository;
    }

    public void criar() {}

    public void atualizar() {}

    public void listar() {}

    public void remover() {}

}
