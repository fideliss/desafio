package com.br.desafio.service;

import com.br.desafio.domain.Funcionario;
import com.br.desafio.dto.FuncionarioDTO;
import com.br.desafio.dto.ProjetoDTO;
import com.br.desafio.mapper.FuncionarioMapper;
import com.br.desafio.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class FuncionarioService {

    private final FuncionarioRepository repository;

    private final FuncionarioMapper mapper;

    private final ProjetoService projetoService;

    public FuncionarioService(FuncionarioRepository repository, FuncionarioMapper mapper, ProjetoService projetoService) {
        this.repository = repository;
        this.mapper = mapper;
        this.projetoService = projetoService;
    }

    public FuncionarioDTO criar(FuncionarioDTO funcionarioDto) {
        Funcionario funcionario = mapper.toEntity(funcionarioDto);

        repository.save(funcionario);

        return mapper.toDto(funcionario);
    }

    public FuncionarioDTO atualizar(FuncionarioDTO funcionarioDto) {
        Optional<Funcionario> funcionarioOp = repository.findById(funcionarioDto.getId());

        if (funcionarioOp.isPresent()) {
            Funcionario funcionario = funcionarioOp.get();
            funcionario.setNome(funcionarioDto.getNome());
            funcionario.setCpf(funcionarioDto.getCpf());
            funcionario.setDataNascimento(funcionarioDto.getDataNascimento());
            funcionario.setSexo(funcionarioDto.getSexo());

            if (Objects.nonNull(funcionarioDto.getEndereco())) {
                funcionario.getEndereco().setPais(funcionarioDto.getEndereco().getPais());
                funcionario.getEndereco().setUf(funcionarioDto.getEndereco().getUf());
                funcionario.getEndereco().setCidade(funcionarioDto.getEndereco().getCidade());
                funcionario.getEndereco().setCep(funcionarioDto.getEndereco().getCep());
            }

            if (Objects.nonNull(funcionarioDto.getSupervisor())) {
                if (Objects.isNull(funcionario.getSupervisor())) {
                    funcionario.setSupervisor(new Funcionario());
                }
                funcionario.getSupervisor().setId(funcionarioDto.getSupervisor().getId());
            }

            repository.save(funcionario);

            funcionarioDto = mapper.toDto(funcionario);
        }

        return funcionarioDto;
    }

    public List<FuncionarioDTO> listar() {
        List<Funcionario> funcionarios = repository.findAll();

        return mapper.toDto(funcionarios);
    }

    public FuncionarioDTO buscar(Long id) {
        Optional<Funcionario> funcionarioOp = repository.findById(id);

        return funcionarioOp.map(mapper::toDto).orElse(null);
    }

    public void remover(Long id) {
        repository.deleteById(id);
    }

    public Set<FuncionarioDTO> buscarPorProjeto(Long projetoId) {
        ProjetoDTO projetoDTO = projetoService.buscar(projetoId);

        return projetoDTO.getFuncionarios();
    }

    public List<FuncionarioDTO> buscarPorNome(String nome) {
        List<Funcionario> funcionarios = repository.buscarPorNome(nome);
        return mapper.toDto(funcionarios);
    }

    public List<FuncionarioDTO> buscarPorSupervisor(Long supervisorId) {
        List<Funcionario> funcionarios = repository.buscarPorSupervisor(supervisorId);
        return mapper.toDto(funcionarios);
    }
}
