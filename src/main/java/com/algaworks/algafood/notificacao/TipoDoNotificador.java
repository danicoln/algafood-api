package com.algaworks.algafood.notificacao;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * Anotação customizada, que também é um qualificador
 * */
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface TipoDoNotificador{

    NivelUrgencia value();
}