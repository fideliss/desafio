package com.br.desafio.controller;

import com.br.desafio.dto.ProjetoDTO;
import com.br.desafio.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projeto")
public class ProjetoController {

    @Autowired
    private ProjetoService service;

    @PostMapping
    public ResponseEntity<ProjetoDTO> criar(@RequestBody ProjetoDTO projetoDto) {
        ProjetoDTO projeto = service.criar(projetoDto);
        return ResponseEntity.ok(projeto);
    }

    @PutMapping
    public ResponseEntity<ProjetoDTO> atualizar(@RequestBody ProjetoDTO projetoDto) {
        ProjetoDTO projeto = service.atualizar(projetoDto);
        return ResponseEntity.ok(projeto);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProjetoDTO>> listar() {
        List<ProjetoDTO> projetos = service.listar();
        return ResponseEntity.ok(projetos);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<ProjetoDTO> buscar(@PathVariable("id") Long id) {
        ProjetoDTO projeto = service.buscar(id);
        return ResponseEntity.ok(projeto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable("id") Long id) {
        service.remover(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/buscar-por-funcionario/{funcionarioId}")
    public ResponseEntity<List<ProjetoDTO>> buscarPorFuncionario(@PathVariable("funcionarioId") Long funcionarioId) {
        List<ProjetoDTO> projetos = service.buscarPorFuncionario(funcionarioId);
        return ResponseEntity.ok(projetos);
    }

}
