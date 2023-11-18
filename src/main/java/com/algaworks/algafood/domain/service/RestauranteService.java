
package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository repository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public List<Restaurante> listar(){
        return repository.findAll();
    }
    /**Exemplo: testando o fetch lazy*/
//    public List<Restaurante> listar(){
//        List<Restaurante> restaurantes = repository.findAll();
//        System.out.println("O nome da cozinha é: ");
//        System.out.println(restaurantes.get(0).getCozinha().getNome());
//        return restaurantes;
//    }

    public Restaurante buscar(Long restauranteId){
        Optional<Restaurante> restaurante = repository.findById(restauranteId);
        if(restaurante.get().getId().equals(restauranteId)){
            return restaurante.get();
        }
        return null;
    }

    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId(); // atribuição de variável
        Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format("Não existe cadastro de cozinha com código %d", cozinhaId)));

        restaurante.setCozinha(cozinha);
        return repository.save(restaurante);
    }

    public void excluir(Long restauranteId) {
        try {
            repository.deleteById(restauranteId);

        } catch (EmptyResultDataAccessException emptyException) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de restaurante com o código %d",
                            restauranteId));

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Restaurante de código %d não pode ser removida, pois está em uso",
                            restauranteId));
        }
    }
}
