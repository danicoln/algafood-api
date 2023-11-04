package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCozinhaService {

    @Autowired
    private CozinhaRepository repository;

    public Cozinha salvar(Cozinha cozinha) {
        return repository.salvar(cozinha);
    }

    public void excluir(Long cozinhaId) {
        /**Fazemos o tratamento de exceções aqui na classe de serviço
         * Mas na classe de serviço, não pode ter acesso às implementações
         * da api.
         * Por exemplo, retornar um ResponseEntity, não pode ser realizada de forma alguma*/
        try {
            repository.remover(cozinhaId);

        } catch (EmptyResultDataAccessException emptyException) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de cozinha com o código %d",
                            cozinhaId));

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Cozinha de código %d não pode ser removida, pois está em uso",
                            cozinhaId)); //exception de negócio
        }
    }

}
