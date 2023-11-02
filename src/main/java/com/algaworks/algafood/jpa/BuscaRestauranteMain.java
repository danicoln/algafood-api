package com.algaworks.algafood.jpa;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class BuscaRestauranteMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestauranteRepository repository = applicationContext.getBean(RestauranteRepository.class);

        List<Restaurante> restaurantes = repository.listar();

        for(Restaurante restaurante : restaurantes){
            System.out.printf("%s - %f - %s\n", restaurante.getNome(),
                    restaurante.getTaxaFrete(), restaurante.getCozinha().getNome());
        }

    }
}
