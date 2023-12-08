package com.algaworks.algafood.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL) // Só incluirá na representação JSON as propriedades que não estiverem nulas.
@Getter
@Builder
public class Problem {

    /**
     * Padronizando o formato de problemas no corpo de respostas com a RFC 7807
     * */
    private Integer status;
    private String type;
    private String title;
    private String detail;

    private String userMessage;

}
