package com.algaworks.algafood.domain.model.dto.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class EstadoInput {

    @NotBlank
    private String nome;

}
