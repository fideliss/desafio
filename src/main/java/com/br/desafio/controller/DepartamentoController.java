package com.br.desafio.controller;

import com.br.desafio.domain.Departamento;
import com.br.desafio.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/departamento")
public class DepartamentoController {

    @Autowired
    private DepartamentoService service;

    @PostMapping
    public ResponseEntity<Departamento> criar() {
        service.criar();
        return ResponseEntity.ok(new Departamento());
    }

}
