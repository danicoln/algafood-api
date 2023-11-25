package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
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
    private RestauranteRepository repository;

    @Autowired
    private RestauranteService service;

    @GetMapping
    public List<Restaurante> listar() {
        return repository.findAll();
    }

    @GetMapping("/{restauranteId}")
    public Restaurante buscar(@PathVariable Long restauranteId) {
        return service.buscarOuFalhar(restauranteId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurante adicionar (@RequestBody Restaurante restaurante) {
        return service.salvar(restaurante);
    }

    @PutMapping("/{restauranteId}")
    public Restaurante atualizar(@PathVariable Long restauranteId,
                                       @RequestBody Restaurante restaurante) {
            Restaurante restauranteAtual = service.buscarOuFalhar(restauranteId);

            BeanUtils.copyProperties(restaurante, restauranteAtual,
                    "id", "formasPagamentos",
                    "endereco", "dataCadastro", "produtos");

            return service.salvar(restauranteAtual);
    }

    @PatchMapping("/{restauranteId}")
    public Restaurante atualizarParcial(@PathVariable Long restauranteId,
                                              @RequestBody Map<String, Object> campos) {

        Restaurante restauranteAtual = service.buscar(restauranteId);

        merge(campos, restauranteAtual);
        return atualizar(restauranteId, restauranteAtual);
    }

    /**
     * A função deste método é "mesclar" o valor 1 (dadosOrigem) para o valor 2 (restauranteDestino)
     */
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
    public void remover(@PathVariable Long restauranteId) {
        service.excluir(restauranteId);
    }

}
