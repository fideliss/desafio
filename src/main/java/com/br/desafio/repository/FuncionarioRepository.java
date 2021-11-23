package com.br.desafio.repository;

import com.br.desafio.domain.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    @Query(value = "SELECT * FROM funcionario f WHERE f.nome LIKE %:nome%", nativeQuery = true)
    List<Funcionario> buscarPorNome(@Param("nome") String nome);

}
