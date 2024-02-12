package com.algaworks.algafood.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RestauranteModel {

    private Long id;
    private String nome;
    private BigDecimal frete;
    private CozinhaModel cozinha;
    private String nomeCozinha;
    private Long idCozinha;
}
