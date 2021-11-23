package com.br.desafio.service;

import com.br.desafio.domain.Departamento;
import com.br.desafio.dto.DepartamentoDTO;
import com.br.desafio.mapper.DepartamentoMapper;
import com.br.desafio.repository.DepartamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoService {

    private final DepartamentoRepository repository;

    private final DepartamentoMapper mapper;

    public DepartamentoService(DepartamentoRepository repository, DepartamentoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public DepartamentoDTO criar(DepartamentoDTO departamentoDto) {
        Departamento departamento = mapper.toEntity(departamentoDto);
        repository.save(departamento);

        return mapper.toDto(departamento);
    }

    public DepartamentoDTO atualizar(DepartamentoDTO departamentoDto) {
        Optional<Departamento> departamentoOp = repository.findById(departamentoDto.getId());

        if (departamentoOp.isPresent()) {
            Departamento departamento = departamentoOp.get();
            departamento.setNome(departamentoDto.getNome());
            departamento.setNumero(departamentoDto.getNumero());

            repository.save(departamento);

            departamentoDto = mapper.toDto(departamento);
        }

        return departamentoDto;
    }

    public List<DepartamentoDTO> listar() {
        List<Departamento> departamentos = repository.findAll();

        return mapper.toDto(departamentos);
    }

    public DepartamentoDTO buscar(Long id) {
        Optional<Departamento> departamentoOp = repository.findById(id);

        return departamentoOp.map(mapper::toDto).orElse(null);
    }

    public void remover(Long id) {
        repository.deleteById(id);
    }

}
