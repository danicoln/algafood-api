package com.algaworks.algafood.api.exceptionhandler;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder // padrão de projeto, para construir de forma mais fluente
public class Problema {

    private LocalDateTime dataHora;
    private  String mensagem;
}
