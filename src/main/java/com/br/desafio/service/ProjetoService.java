package com.br.desafio.service;

import com.br.desafio.domain.Departamento;
import com.br.desafio.domain.Funcionario;
import com.br.desafio.domain.Projeto;
import com.br.desafio.dto.FuncionarioDTO;
import com.br.desafio.dto.ProjetoDTO;
import com.br.desafio.mapper.ProjetoMapper;
import com.br.desafio.repository.ProjetoRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class ProjetoService {

    private final ProjetoRepository repository;

    private final ProjetoMapper mapper;

    public ProjetoService(ProjetoRepository repository, ProjetoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ProjetoDTO criar(ProjetoDTO projetoDto) {
        Projeto projeto = mapper.toEntity(projetoDto);
        repository.save(projeto);

        return mapper.toDto(projeto);
    }

    public ProjetoDTO atualizar(ProjetoDTO projetoDto) {
        Optional<Projeto> projetoOp = repository.findById(projetoDto.getId());

        if (projetoOp.isPresent()) {
            Projeto projeto = projetoOp.get();
            projeto.setNome(projetoDto.getNome());

            if (Objects.nonNull(projetoDto.getDepartamento())) {
                if (Objects.isNull(projeto.getDepartamento())) {
                    projeto.setDepartamento(new Departamento());
                }
                projeto.getDepartamento().setId(projetoDto.getDepartamento().getId());
            }

            adicionarRemoverFuncionarios(projetoDto, projeto);

            repository.save(projeto);

            projetoDto = mapper.toDto(projeto);
        }

        return projetoDto;
    }

    private void adicionarRemoverFuncionarios(ProjetoDTO projetoDto, Projeto projeto) {
        if (CollectionUtils.isEmpty(projetoDto.getFuncionarios())) {
            projeto.setFuncionarios(new HashSet<>());
        } else {
            Set<FuncionarioDTO> funcionariosDtos = projetoDto.getFuncionarios();
            Set<Funcionario> funcionarios = projeto.getFuncionarios();

            if (CollectionUtils.isEmpty(funcionarios)) {
                projeto.setFuncionarios(new HashSet<>());
                funcionariosDtos.forEach(funcionarioDTO -> {
                    Funcionario funcionario = montarFuncionarioProjeto(funcionarioDTO);

                    funcionarios.add(funcionario);
                });
            } else {
                List<Funcionario> funcionariosRemover = new ArrayList<>();
                List<FuncionarioDTO> funcionariosAdicionar = new ArrayList<>();

                funcionarios.forEach(funcionario -> {
                    boolean funcionarioRemovido = funcionariosDtos.stream()
                            .filter(funcionarioDTO -> funcionarioDTO.getId().equals(funcionario.getId()))
                            .findFirst()
                            .isEmpty();

                    if (funcionarioRemovido) {
                        funcionariosRemover.add(funcionario);
                    }
                });

                funcionariosDtos.forEach(funcionarioDTO -> {
                    boolean adicionarFuncionario = funcionarios.stream()
                            .filter(funcionario -> funcionarioDTO.getId().equals(funcionario.getId()))
                            .findFirst()
                            .isEmpty();

                    if (adicionarFuncionario) {
                        funcionariosAdicionar.add(funcionarioDTO);
                    }
                });

                funcionarios.removeAll(funcionariosRemover);

                funcionariosAdicionar.forEach(funcionarioDTO -> {
                    Funcionario funcionario = montarFuncionarioProjeto(funcionarioDTO);

                    funcionarios.add(funcionario);
                });
            }
        }
    }

    private Funcionario montarFuncionarioProjeto(FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(funcionarioDTO.getId());
        return funcionario;
    }

    public List<ProjetoDTO> listar() {
        List<Projeto> projetos = repository.findAll();

        return mapper.toDto(projetos);
    }

    public ProjetoDTO buscar(Long id) {
        Optional<Projeto> projetoOp = repository.findById(id);

        return projetoOp.map(mapper::toDto).orElse(null);
    }

    public void remover(Long id) {
        repository.deleteById(id);
    }

    public List<ProjetoDTO> buscarPorFuncionario(Long funcionarioId) {
        List<Projeto> projetos = repository.buscarPorFuncionario(funcionarioId);
        return mapper.toDto(projetos);
    }

}
