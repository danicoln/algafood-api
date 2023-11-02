package com.algaworks.algafood.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

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

    @Column(nullable = false) // este parâmetro nullable=false significa que essas colunas não aceitam valores nulos
    private String nome;
    
}
