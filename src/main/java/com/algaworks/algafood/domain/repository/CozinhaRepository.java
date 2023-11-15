package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

    /**
     *A palavra "Containing" no final, faz a mesma coisa do
     * exemplo usado com o "like" em aulas anteriores
     * */
    List<Cozinha> findTodasByNomeContaining(String nome);

    Optional<Cozinha> findByNome(String nome);

}
