package com.br.desafio.service;

import com.br.desafio.domain.Endereco;
import com.br.desafio.dto.EnderecoDTO;
import com.br.desafio.mapper.EnderecoMapper;
import com.br.desafio.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    private final EnderecoRepository repository;

    private final EnderecoMapper mapper;

    public EnderecoService(EnderecoRepository repository, EnderecoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public EnderecoDTO criar(EnderecoDTO enderecoDto) {
        Endereco endereco = mapper.toEntity(enderecoDto);
        repository.save(endereco);

        return mapper.toDto(endereco);
    }

    public EnderecoDTO atualizar(EnderecoDTO enderecoDto) {
        Optional<Endereco> enderecoOp = repository.findById(enderecoDto.getId());

        if (enderecoOp.isPresent()) {
            Endereco endereco = enderecoOp.get();
            endereco.setPais(enderecoDto.getPais());
            endereco.setUf(enderecoDto.getUf());
            endereco.setCidade(enderecoDto.getCidade());
            endereco.setRua(enderecoDto.getRua());
            endereco.setCep(enderecoDto.getCep());

            repository.save(endereco);

            enderecoDto = mapper.toDto(endereco);
        }

        return enderecoDto;
    }

    public List<EnderecoDTO> listar() {
        List<Endereco> enderecos = repository.findAll();

        return mapper.toDto(enderecos);
    }

    public EnderecoDTO buscar(Long id) {
        Optional<Endereco> enderecoOp = repository.findById(id);

        return enderecoOp.map(mapper::toDto).orElse(null);
    }

    public void remover(Long id) {
        repository.deleteById(id);
    }

}
