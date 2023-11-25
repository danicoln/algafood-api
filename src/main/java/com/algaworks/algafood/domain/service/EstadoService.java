package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {

    public static final String ESTADO_NAO_ENCONTRADO = "Não existe um Estado com o código %d";
    public static final String ESTADO_EM_USO = "Entidade de código %d não pode ser removida, pois já está em uso";


    @Autowired
    private EstadoRepository repository;

    public Estado salvar(Estado estado) {
        return repository.save(estado);
    }

    public void excluir(Long estadoId) {
        try {
            repository.deleteById(estadoId);

        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format(ESTADO_NAO_ENCONTRADO,
                            estadoId));

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(ESTADO_EM_USO,
                            estadoId));
        }

    }

    public Estado buscarOuFalhar(Long estadoId) {
        return repository.findById(estadoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(ESTADO_NAO_ENCONTRADO,
                                estadoId)));
    }
}
