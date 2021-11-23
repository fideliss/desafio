package com.br.desafio.service;

import com.br.desafio.domain.Departamento;
import com.br.desafio.dto.DepartamentoDTO;
import com.br.desafio.dto.FuncionarioDTO;
import com.br.desafio.dto.ProjetoDTO;
import com.br.desafio.enumeration.StatusOrcamento;
import com.br.desafio.mapper.DepartamentoMapper;
import com.br.desafio.repository.DepartamentoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class DepartamentoService {

    private final DepartamentoRepository repository;

    private final DepartamentoMapper mapper;

    private final ProjetoService projetoService;

    private final FuncionarioService funcionarioService;

    public DepartamentoService(DepartamentoRepository repository, DepartamentoMapper mapper,
                               ProjetoService projetoService, FuncionarioService funcionarioService) {
        this.repository = repository;
        this.mapper = mapper;
        this.projetoService = projetoService;
        this.funcionarioService = funcionarioService;
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
            departamento.setOrcamento(departamentoDto.getOrcamento());
            departamento.setDataInicio(departamentoDto.getDataInicio());
            departamento.setDataFim(departamentoDto.getDataFim());

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

    public StatusOrcamento verificarStatusOrcamento(Long departamentoId) {
        Optional<Departamento> departamentoOp = repository.findById(departamentoId);

        if (departamentoOp.isPresent()) {
            Departamento departamento = departamentoOp.get();

            AtomicReference<BigDecimal> totalValor = new AtomicReference<>(BigDecimal.ZERO);
            List<ProjetoDTO> projetos = projetoService.buscarPorDepartamento(departamentoId);
            projetos.forEach(projetoDTO -> totalValor.getAndAccumulate(projetoDTO.getValor(), BigDecimal::add));

            AtomicReference<BigDecimal> totalSalario = new AtomicReference<>(BigDecimal.ZERO);
            projetos.forEach(projetoDTO -> {
                Set<FuncionarioDTO> funcionarioDTOS = funcionarioService.buscarPorProjeto(projetoDTO.getId());
                funcionarioDTOS.forEach(funcionarioDTO -> totalSalario.getAndAccumulate(funcionarioDTO.getSalario(), BigDecimal::add));
            });

            totalValor.getAndAccumulate(totalSalario.get(), BigDecimal::add);

            int i = departamento.getOrcamento().compareTo(totalValor.get());

            if (i < 0) {
                return StatusOrcamento.VERMELHO;
            } else if (i == 0) {
                return StatusOrcamento.AMARELO;
            } else {
                return StatusOrcamento.VERDE;
            }
        }

        return null;
    }

}
