package com.br.desafio.service;

import com.br.desafio.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {

    private final FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    public void criar() {}

    public void atualizar() {}

    public void listar() {}

    public void remover() {}

}
