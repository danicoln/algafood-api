package com.algaworks.algafood;

import com.algaworks.algafood.domain.repository.CustomJpaRepository;
import com.algaworks.algafood.infrastructure.repository.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
/**Com essa anotação, customizamos uma propriedade repositoryBaseClass
 *Nesta propriedade, inserimos a classe base, precisa ser a nossa Impl
 *Desta forma, substituímos a implementação do repositório base
 * dexando ser de SimpleJpaRepository para CustomJpaRepository. */
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class AlgafoodApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgafoodApiApplication.class, args);
	}

}
