package com.algaworks.algafood.api.dto.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PermissaoModel {

    @NotBlank
    private String nome;

    @NotNull
    private String descricao;
}
