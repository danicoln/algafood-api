package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository repository;

    public List<Restaurante> listar(){
        return repository.listar();
    }

    public Restaurante buscar(Long restauranteId){
        Restaurante restaurante = repository.buscar(restauranteId);
        if(restaurante.getId().equals(restauranteId)){
            return restaurante;
        }
        return null;
    }

    public Restaurante salvar(Restaurante restaurante) {
        return repository.salvar(restaurante);
    }

    public void excluir(Long restauranteId) {
        try {
            repository.remover(restauranteId);

        } catch (EmptyResultDataAccessException emptyException) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de restaurante com o código %d",
                            restauranteId));

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Restaurante de código %d não pode ser removida, pois está em uso",
                            restauranteId)); //exception de negócio
        }
    }
}