package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.service.ProdutoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping
    public List<Produto> listar() {
        return service.listar();
    }

    @GetMapping("/{produtoId}")
    public ResponseEntity<Produto> buscar(@PathVariable Long produtoId) {
        Produto produto = service.buscar(produtoId);
        if (produto != null) {
            return ResponseEntity.ok(produto);
        }
        return ResponseEntity.notFound().build();

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> salvar(@RequestBody Produto produto) {
        try {
            produto = service.salvar(produto); // atribuição de variável

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(produto);

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage()); // passando a mensagem de erro
            /**Obs.:
             * O retorno do método é um ResponseEntity, mas passamos ? para que seja
             * qualquer tipo, sendo assim, podemos passar uma string no body por ex. (e.getMessage)*/

        }
    }

    @PutMapping("/{produtoId}")
    public ResponseEntity<?> atualizar(@PathVariable Long produtoId,
                                       @RequestBody Produto produto) {
        try {
            Produto produtoAtual = service.buscar(produtoId);

            if (produtoAtual != null) {
                BeanUtils.copyProperties(produto, produtoAtual, "id", "restaurante");

                produtoAtual = service.salvar(produtoAtual);
                return ResponseEntity.ok(produtoAtual);
            }

            return ResponseEntity.notFound().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{produtoId}")
    public ResponseEntity<Produto> remover(@PathVariable Long produtoId) {
        try {
            service.excluir(produtoId);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
