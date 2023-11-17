package com.algaworks.algafood.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) // este parâmetro nullable=false significa que essas colunas não aceitam valores nulos
    private String nome;
    @Column(name = "taxa_frete", nullable = false)
    private BigDecimal taxaFrete;

    @ManyToOne
    @JoinColumn(name = "cozinha_id", nullable = false)
    private Cozinha cozinha;

    @ManyToMany
    @JoinTable(name = "restaurante_forma_pagamento", //customiza o nome da tabela
            joinColumns = @JoinColumn(name = "restaurante_id"),// a propriedade "joinColumn" define qual o nome da tabela intermediaria que associa à tabela restaurante
    inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id")) // Em contra partida, precisamos informar o inverso, definir o nome que faz referencia à tabela inversa.
    private List<FormaPagamento> formasPagamentos = new ArrayList<>();

}
