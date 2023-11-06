package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.RestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteService service;

    @GetMapping
    public List<Restaurante> listar() {
        return service.listar();
    }

    @GetMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId) {
        Restaurante restaurante = service.buscar(restauranteId);
        if (restaurante != null) {
            return ResponseEntity.ok(restaurante);
        }
        return ResponseEntity.notFound().build();

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> salvar(@RequestBody Restaurante restaurante) {
        try {
            restaurante = service.salvar(restaurante); // atribuição de variável

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(restaurante);

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage()); // passando a mensagem de erro
            /**Obs.:
             * O retorno do método é um ResponseEntity, mas passamos ? para que seja
             * qualquer tipo, sendo assim, podemos passar uma string no body por ex. (e.getMessage)*/

        }
    }

    @PutMapping("/{restauranteId}")
    public ResponseEntity<?> atualizar(@PathVariable Long restauranteId,
                                       @RequestBody Restaurante restaurante) {
        try {
            Restaurante restauranteAtual = service.buscar(restauranteId);

            if (restauranteAtual != null) {
                BeanUtils.copyProperties(restaurante, restauranteAtual, "id");

                restauranteAtual = service.salvar(restauranteAtual);
                return ResponseEntity.ok(restauranteAtual);
            }

            return ResponseEntity.notFound().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{restauranteId}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId,
                                              @RequestBody Map<String, Object> campos) {

        Restaurante restauranteAtual = service.buscar(restauranteId);
        /**Observação:
         * Precisamos atribuir os valores de "campos" para a variável "restaurante"
         * */

        if (restauranteAtual == null) {
            return ResponseEntity.notFound().build();
        }

        merge(campos, restauranteAtual);
        return atualizar(restauranteId, restauranteAtual);
    }

    /**A função deste método é "mesclar" o valor 1 (dadosOrigem) para o valor 2 (restauranteDestino)*/
    private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {

        /**
         * ObjectMapper do pacote Jackson, é responsável por serializar(converter)
         * objetos java em json e vice versa.*/
        ObjectMapper objectMapper = new ObjectMapper();

        /**
         * Aqui, convertemos os dadosOrigem para um tipo Restaurante*/
        Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

        System.out.println(restauranteOrigem);

        /**Atribuimos as propriedades à variável dadosOrigem*/
        dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
            /**Usamos Field do Java Lang, para representar um atributo da classe Restaurante
             * que iremos modificar*/
            Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
            field.setAccessible(true);

            /**com o método getField(), buscamos o valor da propriedade representada
             * pela variável field e passamos para a variavel restauranteOrigem
             * a variável novoValor já está convertida para o tipo Restaurante.*/
            Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

            System.out.println(nomePropriedade + " = " + valorPropriedade + " = " + novoValor);

            /**Inspeciona os objetos java e altera em tempo de execução*/
            ReflectionUtils.setField(field, restauranteDestino, novoValor);
        });
    }

    @DeleteMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> remover(@PathVariable Long restauranteId) {
        try {
            service.excluir(restauranteId);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
