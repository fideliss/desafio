package com.br.desafio.controller;

import com.br.desafio.dto.DepartamentoDTO;
import com.br.desafio.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departamento")
public class DepartamentoController {

    @Autowired
    private DepartamentoService service;

    @PostMapping
    public ResponseEntity<DepartamentoDTO> criar(@RequestBody DepartamentoDTO departamentoDto) {
        DepartamentoDTO departamento = service.criar(departamentoDto);
        return ResponseEntity.ok(departamento);
    }

    @PutMapping
    public ResponseEntity<DepartamentoDTO> atualizar(@RequestBody DepartamentoDTO departamentoDto) {
        DepartamentoDTO departamento = service.atualizar(departamentoDto);
        return ResponseEntity.ok(departamento);
    }

    @GetMapping("/list")
    public ResponseEntity<List<DepartamentoDTO>> listar() {
        List<DepartamentoDTO> departamentos = service.listar();
        return ResponseEntity.ok(departamentos);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<DepartamentoDTO> buscar(@PathVariable("id") Long id) {
        DepartamentoDTO departamento = service.buscar(id);
        return ResponseEntity.ok(departamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable("id") Long id) {
        service.remover(id);
        return ResponseEntity.ok().build();
    }

}
