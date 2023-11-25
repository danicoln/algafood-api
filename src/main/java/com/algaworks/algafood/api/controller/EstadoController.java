package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.EstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository repository;

    @Autowired
    private EstadoService service;

    @GetMapping
    public List<Estado> listar() {
        return repository.findAll();
    }

    @GetMapping("/{estadoId}")
    public Estado buscar(@PathVariable Long estadoId) {
        return service.buscarOuFalhar(estadoId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado adicionar(@RequestBody Estado estado) {
        return service.salvar(estado);
    }

    @PutMapping("/{estadoId}")
    public Estado atualizar(@PathVariable Long estadoId,
                                            @RequestBody Estado estado) {

        Estado estadoAtual = service.buscarOuFalhar(estadoId);
        BeanUtils.copyProperties(estado, estadoAtual, "id");
        return service.salvar(estadoAtual);
    }

    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long estadoId) {
        service.excluir(estadoId);
    }
}
