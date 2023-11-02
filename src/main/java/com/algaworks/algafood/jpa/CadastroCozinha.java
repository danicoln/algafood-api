package com.algaworks.algafood.jpa;

import com.algaworks.algafood.domain.model.Cozinha;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class CadastroCozinha {

    @PersistenceContext
    private EntityManager manager;

    public List<Cozinha> listar() {
//        TypedQuery<Cozinha> query = manager.createQuery("from Cozinha", Cozinha.class);
//        return query.getResultList();
        return manager.createQuery("from Cozinha", Cozinha.class).getResultList(); // codigo melhorado
    }

    /**
     * Este método adiciona o novo obj através do retorno
     * manager.merge().
     * Este merge, mescla o estado de determinada entidade no
     * contexto de persistência atual. Retorna a instância
     * gerenciada na qual o estado foi mergiado (mesclado)*/

    @Transactional //Esta anotação faz com que o método seja executado dentro de uma transação
    public Cozinha adicionar(Cozinha cozinha) {
        return manager.merge(cozinha);
    }
}
