package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.core.validation.ValidacaoException;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.CozinhaModel;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.model.RestauranteModel;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.RestauranteService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.ValidationException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository repository;

    @Autowired
    private RestauranteService service;

    @Autowired
    private SmartValidator validator;

    @GetMapping
    public List<RestauranteModel> listar() {
        return toCollectionModel(repository.findAll());
    }

    @GetMapping("/{restauranteId}")
    public RestauranteModel buscar(@PathVariable Long restauranteId) {
        Restaurante restaurante = service.buscarOuFalhar(restauranteId);

        return toModel(restaurante);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteModel adicionar(@RequestBody @Valid Restaurante restaurante) {
        try{
            return toModel(service.salvar(restaurante));
        } catch (CozinhaNaoEncontradaException e){
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}")
    public RestauranteModel atualizar(@PathVariable Long restauranteId,
                                 @RequestBody @Valid Restaurante restaurante) {
        try {
            Restaurante restauranteAtual = service.buscarOuFalhar(restauranteId);

            BeanUtils.copyProperties(restaurante, restauranteAtual,
                    "id", "formasPagamentos",
                    "endereco", "dataCadastro", "produtos");

            return toModel(service.salvar(restauranteAtual));
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }

    }
    
    @DeleteMapping("/{restauranteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long restauranteId) {
        service.excluir(restauranteId);
    }

    private RestauranteModel toModel(Restaurante restaurante) {
        CozinhaModel cozinhaModel = new CozinhaModel();
        cozinhaModel.setId(restaurante.getCozinha().getId());
        cozinhaModel.setNome(restaurante.getCozinha().getNome());

        RestauranteModel model = new RestauranteModel();
        model.setId(restaurante.getId());
        model.setNome(restaurante.getNome());
        model.setTaxaFrete(restaurante.getTaxaFrete());
        model.setCozinha(cozinhaModel);
        return model;
    }

    private List<RestauranteModel> toCollectionModel(List<Restaurante> restaurantes){
        return restaurantes.stream()
                .map(restaurante -> toModel(restaurante))
                .collect(Collectors.toList());
    }

}
