package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository repository;

    public Cidade salvar(Cidade cidade) {
        return repository.save(cidade);
    }

    public void excluir(Long cidadeId) {
        try {
            if (!repository.existsById(cidadeId)) {
                throw new EntidadeNaoEncontradaException(
                        String.format("Não existe uma cidade com o código %d",
                                cidadeId));
            }
            repository.deleteById(cidadeId);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Entidade de código %d não pode ser removida, pois já está em uso",
                            cidadeId));
        }

    }
}
