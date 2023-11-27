
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

    public static final String ENTIDADE_NAO_ENCONTRADA = "Não existe um cadastro de restaurante com o código %d";
    public static final String ENTIDADE_EM_USO = "Restaurante de código %d não pode ser removida, pois está em uso";

    @Autowired
    private RestauranteRepository repository;

    @Autowired
    private CozinhaRepository cozinhaRepository;
    @Autowired CadastroCozinhaService cozinhaService;

    public List<Restaurante> listar() {
        return repository.findAll();
    }


    public Restaurante buscar(Long restauranteId) {
        Optional<Restaurante> restaurante = repository.findById(restauranteId);
        if (restaurante.get().getId().equals(restauranteId)) {
            return restaurante.get();
        }
        return null;
    }

    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();

        Cozinha cozinha = cozinhaService.buscarOuFalhar(cozinhaId);

        // com a linha anterior reaproveitamos o codigo, substituindo o código abaixo.

//        Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
//                .orElseThrow(() -> new EntidadeNaoEncontradaException(
//                        String.format(ENTIDADE_NAO_ENCONTRADA, cozinhaId)));

        restaurante.setCozinha(cozinha);
        return repository.save(restaurante);
    }

    public void excluir(Long restauranteId) {
        try {
            repository.deleteById(restauranteId);

        } catch (EmptyResultDataAccessException emptyException) {
            throw new EntidadeNaoEncontradaException(
                    String.format(ENTIDADE_NAO_ENCONTRADA,
                            restauranteId));

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(ENTIDADE_EM_USO,
                            restauranteId));
        }
    }

    public Restaurante buscarOuFalhar(Long restauranteId) {
        return repository.findById(restauranteId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(ENTIDADE_NAO_ENCONTRADA,
                                restauranteId)));
    }
}
