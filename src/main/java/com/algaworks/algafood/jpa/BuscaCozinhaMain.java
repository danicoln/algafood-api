package com.algaworks.algafood.jpa;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cozinha;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class BuscaCozinhaMain {
    public static void main(String[] args) {

        /**
         * O código abaixo serve para rodar e parar a aplicação, sem a necessidade da aplicação ficar esperando
         * requisições como é a aplicação real*/
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        /**
         * Abaixo iremos rodar o código desta aula*/

        CadastroCozinha cadastroCozinha = applicationContext.getBean(CadastroCozinha.class);

        Cozinha cozinha = cadastroCozinha.buscar(1L);

        System.out.println(cozinha.getNome());
    }
}
