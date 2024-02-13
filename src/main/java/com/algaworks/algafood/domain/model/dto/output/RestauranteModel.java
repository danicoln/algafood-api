package com.algaworks.algafood.domain.model.dto.output;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RestauranteModel {

    private Long id;
    private String nome;
    private BigDecimal precoFrete;
    private CozinhaModel cozinha;
    private Boolean ativo;
}
