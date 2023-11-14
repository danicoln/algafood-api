package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CozinhaRepositoryImpl implements CozinhaRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cozinha> listar() {
        return manager.createQuery("from Cozinha", Cozinha.class)
                .getResultList();
    }

    @Override
    public List<Cozinha> consultarPorNome(String nomeCozinha) {
        /**
         * // like significa que queremos uma Cozinha que contenha uma parte do nome no caso.
         * */
        return manager.createQuery("from Cozinha where nome like = :nome", Cozinha.class)
                /**
                 * os "%" inserido significa que queremos uma Cozinha
                 * que contenha ":nome" tanto para a esqueda quanto
                 * para a direita
                 * */
                .setParameter("nome", "%" + nomeCozinha + "%")
                .getResultList();
    }

    @Override
    public Cozinha buscar(Long id) {
        return manager.find(Cozinha.class, id);
    }

    @Transactional
    @Override
    public Cozinha salvar(Cozinha cozinha) {
        return manager.merge(cozinha);
    }

    @Transactional
    @Override
    public void remover(Long id) {
        Cozinha cozinha = buscar(id);

        if(cozinha == null){
            /**
             * Exeção do springframework.
             * Abstrai bastante o uso da JPA.
             * Passamos como parâmetro o tamanho que espera, no nosso
             * caso, esperamos pelo menos 1 cozinha*/
            throw new EmptyResultDataAccessException(1);
        }
        manager.remove(cozinha);
    }
}
