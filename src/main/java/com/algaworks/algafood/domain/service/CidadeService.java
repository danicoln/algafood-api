package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CidadeService {

    public static final String CIDADE_NAO_ENCONTRADA = "Não existe uma cidade com o código %d";
    public static final String CIDADE_EM_USO = "Entidade de código %d não pode ser removida, pois já está em uso";

    @Autowired
    private CidadeRepository repository;

    @Autowired
    private EstadoService estadoService;

    public Cidade salvar(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();
        Estado estado = estadoService.buscarOuFalhar(estadoId);
        return repository.save(cidade);
    }

    public void excluir(Long cidadeId) {
        try {
            repository.deleteById(cidadeId);

        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format(CIDADE_NAO_ENCONTRADA,
                            cidadeId));

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(CIDADE_EM_USO,
                            cidadeId));
        }
    }

    public Cidade buscarOuFalhar(Long cidadeId) {
        return repository.findById(cidadeId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(CIDADE_NAO_ENCONTRADA,
                                cidadeId)));
    }
}
