
package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RestauranteService {

    public static final String ENTIDADE_EM_USO = "Restaurante de código %d não pode ser removida, pois está em uso";

    @Autowired
    private RestauranteRepository repository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    CadastroCozinhaService cozinhaService;

    @Autowired
    CidadeService cidadeService;

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

    @Transactional
    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Long cidadeId = restaurante.getEndereco().getCidade().getId();

        Cozinha cozinha = cozinhaService.buscarOuFalhar(cozinhaId);
        Cidade cidade = cidadeService.buscarOuFalhar(cidadeId);

        restaurante.setCozinha(cozinha);
        restaurante.getEndereco().setCidade(cidade);

        return repository.save(restaurante);
    }

    @Transactional
    public void excluir(Long restauranteId) {
        try {
            repository.deleteById(restauranteId);
            repository.flush();

        } catch (EmptyResultDataAccessException emptyException) {
            throw new RestauranteNaoEncontradoException(restauranteId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(ENTIDADE_EM_USO,
                            restauranteId));
        }
    }

    @Transactional
    public void ativar(Long restauranteId){
        // No método buscarOuFalhar já é tradado a exception para quando não for encontrado o restaurante
        Restaurante restauranteAtual = buscarOuFalhar(restauranteId);

        restauranteAtual.ativar();
    }

    @Transactional
    public void inativar(Long restauranteId){
        Restaurante restauranteAtual = buscarOuFalhar(restauranteId);

//        restauranteAtual.setAtivo(false);
        restauranteAtual.inativar();
    }

    public Restaurante buscarOuFalhar(Long restauranteId) {
        return repository.findById(restauranteId)
                .orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));
    }
}
