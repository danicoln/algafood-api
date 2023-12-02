package com.algaworks.algafood.api.exceptionhandler;

import lombok.Getter;

/**
 * Criamos este ProblemType, para não termos que ficar especificando
 * na ApiExceptionHandler. Sendo assim, diminuimos linhas de cód.
 * */
@Getter
public enum ProblemType {

    PROPRIEDADE_IGNORADA("/propriedade-ignorada", "Propriedade Ignorada Por JsonIgnore"),
    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem Incompreesível"),
    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
    PROPRIEDADE_NAO_EXISTE_NA_ENTIDADE("/propriedade-nao-existe-na-entidade", "Propriedade Inexistente na Entidade");

    private String title;
    private String uri;

    ProblemType(String path, String title){
        this.uri = "https://algafood.com.br" + path;
        this.title = title;
    }
}
