package com.algaworks.algafood.api.exceptionhandler;

import lombok.Getter;

/**
 * Criamos este ProblemType, para não termos que ficar especificando
 * na ApiExceptionHandler. Sendo assim, diminuimos linhas de cód.
 * */
@Getter
public enum ProblemType {

    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada");

    private String title;
    private String uri;

    ProblemType(String path, String title){
        this.uri = "https://algafood.com.br" + path;
        this.title = title;
    }
}
