package com.algaworks.algafood.api.model;

import com.algaworks.algafood.domain.model.Cozinha;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

/**
 * A função desta classe é empacotar uma lista de Cozinhas*/
@JacksonXmlRootElement(localName = "cozinhas")
@Data
public class CozinhasXmlWrapper {

    @JsonProperty("cozinha")
    @JacksonXmlElementWrapper(useWrapping = false)//desabilita a tag de embrulho (testar no postman)
    @NonNull
    private List<Cozinha> cozinhas;
}
