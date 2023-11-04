package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroCozinhaService {

    @Autowired
    private CozinhaRepository repository;

    /**
     * Foi decidido usar este método tanto para salvar quanto para atualizar
     * não é uma regra, ou seja, pode ter um método para atualizar tbm*/
    public Cozinha salvar(Cozinha cozinha) {
        return repository.salvar(cozinha);
    }

}
