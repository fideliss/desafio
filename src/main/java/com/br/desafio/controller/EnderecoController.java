package com.br.desafio.controller;

import com.br.desafio.dto.EnderecoDTO;
import com.br.desafio.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @PostMapping
    public ResponseEntity<EnderecoDTO> criar(@RequestBody EnderecoDTO enderecoDto) {
        EnderecoDTO endereco = service.criar(enderecoDto);
        return ResponseEntity.ok(endereco);
    }

    @PutMapping
    public ResponseEntity<EnderecoDTO> atualizar(@RequestBody EnderecoDTO enderecoDto) {
        EnderecoDTO endereco = service.atualizar(enderecoDto);
        return ResponseEntity.ok(endereco);
    }

    @GetMapping("/list")
    public ResponseEntity<List<EnderecoDTO>> listar() {
        List<EnderecoDTO> enderecos = service.listar();
        return ResponseEntity.ok(enderecos);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<EnderecoDTO> buscar(@PathVariable("id") Long id) {
        EnderecoDTO endereco = service.buscar(id);
        return ResponseEntity.ok(endereco);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable("id") Long id) {
        service.remover(id);
        return ResponseEntity.ok().build();
    }

}
