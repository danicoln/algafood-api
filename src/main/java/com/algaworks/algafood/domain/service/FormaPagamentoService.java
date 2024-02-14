package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.FormaPagamentoNaoEncontradaException;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FormaPagamentoService {

    public static final String FORMA_PAGAMENTO_EM_USO
            = "Forma de pagamento de código %d não pode ser removida, pois está em uso";

    @Autowired
    private FormaPagamentoRepository repository;

    public List<FormaPagamento> listar(){
        return repository.findAll();
    }

    public FormaPagamento buscar(Long formaPagamentoId) {
        Optional<FormaPagamento> formaPagamento = repository.findById(formaPagamentoId);
        if(formaPagamento.get().getId().equals(formaPagamentoId)){
            return formaPagamento.get();
        }
        return null;
    }

    @Transactional
    public FormaPagamento salvar(FormaPagamento formaPagamento){
        return repository.save(formaPagamento);
    }

    @Transactional
    public void excluir(Long formaPagamentoId){
        try{
            repository.deleteById(formaPagamentoId);
            repository.flush();
        } catch (EmptyResultDataAccessException emptyException) {
            throw new FormaPagamentoNaoEncontradaException(formaPagamentoId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(FORMA_PAGAMENTO_EM_USO,
                            formaPagamentoId));
        }
    }

    public FormaPagamento buscarOuFalhar(Long formaPagamentoId) {
        return repository.findById(formaPagamentoId)
                .orElseThrow(() -> new FormaPagamentoNaoEncontradaException(formaPagamentoId));
    }
}
