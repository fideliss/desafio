package com.br.desafio.controller;

import com.br.desafio.dto.DepartamentoDTO;
import com.br.desafio.dto.FuncionarioDTO;
import com.br.desafio.service.DepartamentoService;
import com.br.desafio.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @PostMapping
    public ResponseEntity<FuncionarioDTO> criar(@RequestBody FuncionarioDTO funcionarioDto) {
        FuncionarioDTO funcionario = service.criar(funcionarioDto);
        return ResponseEntity.ok(funcionario);
    }

    @PutMapping
    public ResponseEntity<FuncionarioDTO> atualizar(@RequestBody FuncionarioDTO funcionarioDto) {
        FuncionarioDTO funcionario = service.atualizar(funcionarioDto);
        return ResponseEntity.ok(funcionario);
    }

    @GetMapping("/list")
    public ResponseEntity<List<FuncionarioDTO>> listar() {
        List<FuncionarioDTO> funcionarios = service.listar();
        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<FuncionarioDTO> buscar(@PathVariable("id") Long id) {
        FuncionarioDTO funcionario = service.buscar(id);
        return ResponseEntity.ok(funcionario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable("id") Long id) {
        service.remover(id);
        return ResponseEntity.ok().build();
    }

}
