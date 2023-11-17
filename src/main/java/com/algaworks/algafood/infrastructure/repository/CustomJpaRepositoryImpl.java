package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.repository.CustomJpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.util.Optional;

public class CustomJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements
        CustomJpaRepository<T, ID> {

    private EntityManager manager;

    public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);

        this.manager = entityManager;
    }

    @Override
    public Optional<T> buscarPrimeiro() {
        var jpql = "from " + getDomainClass().getName();

        T entity = manager.createQuery(jpql, getDomainClass())
                //gera uma consulta sql limitando os resultados em apenas uma linha
                .setMaxResults(1)
                .getSingleResult(); // retorna apenas um resultado

        return Optional.ofNullable(entity);
    }
}
