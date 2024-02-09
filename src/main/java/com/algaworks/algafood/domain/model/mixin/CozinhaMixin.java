package com.algaworks.algafood.domain.model.mixin;

import com.algaworks.algafood.domain.model.Restaurante;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class CozinhaMixin {

    @JsonProperty("titulo")
    private String nome;

    @JsonIgnore
    private List<Restaurante> restaurantes = new ArrayList<>();
}
