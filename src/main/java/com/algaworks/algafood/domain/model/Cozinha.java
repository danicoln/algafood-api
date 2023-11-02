package com.algaworks.algafood.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Getter
//@Setter
//@EqualsAndHashCode
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)// o parâmetro srve para que o equals e hash seja incluído apenas se for explícito
@Entity
public class Cozinha {

    @EqualsAndHashCode.Include // estamos explicitando o equals e hashcode no atributo id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    
}
