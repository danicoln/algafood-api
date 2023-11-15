package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teste")
public class TesteController {


    @Autowired
    private CozinhaRepository repository;

    @GetMapping("/cozinhas/por-nome")
    /**
     * Por padrão, o bind já é feito por parâmetros da requisição, não sendo
     * obrigatório o uso do "@RequestParam("nome")" no parâmentro do metodo
     * */
    public List<Cozinha> cozinhasPorNome( String nome){
        return repository.findTodasByNome(nome);
    }

    @GetMapping("/cozinhas/unica-por-nome")
    public Optional<Cozinha> cozinhaPorNome(String nome){
        return repository.findByNome(nome);
    }
}
