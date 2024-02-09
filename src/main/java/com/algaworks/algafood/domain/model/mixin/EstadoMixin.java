package com.algaworks.algafood.domain.model.mixin;

import com.algaworks.algafood.domain.model.Cidade;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class EstadoMixin {

    @JsonIgnore
    private List<Cidade> cidades = new ArrayList<>();
}
