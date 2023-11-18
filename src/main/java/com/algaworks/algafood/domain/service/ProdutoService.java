package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.ProdutoRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    public Produto buscar(Long produtoId) {
        Optional<Produto> produto = produtoRepository.findById(produtoId);
        if (produto.get().getId().equals(produtoId)) {
            return produto.get();
        }
        return null;
    }

    public Produto salvar(Produto produto) {
        Long restauranteId = produto.getRestaurante().getId();
        Restaurante restaurante = restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format("Não existe cadastro de restaurante com código %d", restauranteId)));
        produto.setRestaurante(restaurante);
        return produtoRepository.save(produto);
    }

    public void excluir(Long produtoId) {
        try {
            produtoRepository.deleteById(produtoId);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de produto com o código %d",
                            produtoId));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Produto de código %d não pode ser removida, pois está em uso",
                            produtoId));
        }
    }
}
