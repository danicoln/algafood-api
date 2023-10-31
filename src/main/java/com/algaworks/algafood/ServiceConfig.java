package com.algaworks.algafood;

import com.algaworks.algafood.service.AtivacaoClienteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 2º exemplo de método callback
 *
 * Outro método de callback é numa classe de configuração, criar um método
 * onde passa como parâmetro na anotação do @Bean o initMethod e o destroyMethod
 * passando como string o nome do método criado na entidade específica,
 * neste caso de exemplo, os métodos criados de AtivacaoClienteService
 * */
@Configuration
public class ServiceConfig {

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public AtivacaoClienteService service() {
        return new AtivacaoClienteService();
    }
}
