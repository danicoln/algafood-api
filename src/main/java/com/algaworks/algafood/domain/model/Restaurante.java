package com.algaworks.algafood.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    @JsonIgnore
    @Embedded
    private Endereco endereco;

    @JsonIgnore
    // implementação do Hibernate, não no JPA
    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime") //na propriedade columnDefinition, definimos datetime para que os milisegundos não apareça
    private LocalDateTime dataCadastro;

    @JsonIgnore
    // implementação do Hibernate, não no JPA
    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataAtualizacao;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "restaurante_forma_pagamento", //customiza o nome da tabela
            joinColumns = @JoinColumn(name = "restaurante_id"),// a propriedade "joinColumn" define qual o nome da tabela intermediaria que associa à tabela restaurante
    inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id")) // Em contra partida, precisamos informar o inverso, definir o nome que faz referencia à tabela inversa.
    private List<FormaPagamento> formasPagamentos = new ArrayList<>();

}
