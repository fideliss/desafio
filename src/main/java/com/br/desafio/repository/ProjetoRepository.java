package com.br.desafio.repository;

import com.br.desafio.domain.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

    @Query(value = "SELECT * FROM projeto p \n" +
            "JOIN funcionario_projeto fp ON fp.projeto_id = p.id \n" +
            "WHERE fp.funcionario_id = :funcionarioId", nativeQuery = true)
    List<Projeto> buscarPorFuncionario(@Param("funcionarioId") Long funcionarioId);

    @Query(value = "SELECT * FROM projeto p WHERE p.departamento_id = :departamentoId",
            nativeQuery = true)
    List<Projeto> buscarPorDepartamento(@Param("departamentoId") Long departamentoId);

}
