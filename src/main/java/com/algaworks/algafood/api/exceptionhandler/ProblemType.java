package com.algaworks.algafood.api.exceptionhandler;

import lombok.Getter;

/**
 * Criamos este ProblemType, para não termos que ficar especificando
 * na ApiExceptionHandler. Sendo assim, diminuimos linhas de cód.
 * */
@Getter
public enum ProblemType {

    ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de Sistema"),
    PROPRIEDADE_IGNORADA("/propriedade-ignorada", "Propriedade Ignorada Por JsonIgnore"),
    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem Incompreesível"),
    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
    PROPRIEDADE_NAO_EXISTE_NA_ENTIDADE("/propriedade-nao-existe-na-entidade", "Propriedade Inexistente na Entidade"),
    PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro Inválido"),
    DADOS_INVALIDOS("/dados-invalidos","Dados inválidos" );

    private String title;
    private String uri;

    ProblemType(String path, String title){
        this.uri = "https://algafood.com.br" + path;
        this.title = title;
    }
}
