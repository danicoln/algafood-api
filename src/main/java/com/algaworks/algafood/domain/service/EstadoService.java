package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository repository;

    public Estado salvar(Estado estado){
        return repository.save(estado);
    }

    public void excluir(Long estadoId){
        try{
            if(!repository.existsById(estadoId)){
                throw new EntidadeNaoEncontradaException(
                        String.format("Não existe um Estado com o código %d",
                                estadoId));
            }
            repository.deleteById(estadoId);
        } catch(DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(
                    String.format("Entidade de código %d não pode ser removida, pois já está em uso",
                            estadoId));
        }

    }
}
