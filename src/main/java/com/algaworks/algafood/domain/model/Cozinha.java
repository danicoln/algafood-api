package com.algaworks.algafood.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

//@Getter
//@Setter
//@EqualsAndHashCode
@JsonRootName("gastronomia")//muda a propriedade (testar no postman)
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)// o parâmetro srve para que o equals e hash seja incluído apenas se for explícito
@Entity
public class Cozinha {

    @EqualsAndHashCode.Include // estamos explicitando o equals e hashcode no atributo id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @JsonIgnore // ignora a propriedade (testar no postman)
    @JsonProperty("titulo") // a representacao de "nome" será titulo (testar no postman)
    @Column(nullable = false) // este parâmetro nullable=false significa que essas colunas não aceitam valores nulos
    private String nome;
    
}
