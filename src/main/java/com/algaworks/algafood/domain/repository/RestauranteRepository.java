package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestauranteRepository
        extends CustomJpaRepository<Restaurante, Long>,
        RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante> {

    @Query("from Restaurante r join r.cozinha left join fetch r.formasPagamentos")
    List<Restaurante> findAll();

    List<Restaurante> queryByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

    //    @Query("from Restaurante where nome like %:nome% and cozinha.id = :id")
    List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinha);

    // substituimos o método abaixo pelo consultarPorNome
//    List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinha);

    /**
     * Para delimitar a consulta sempre com o primeiro (First)
     */
    Optional<Restaurante> findFirstRestauranteByNomeContaining(String nome);

    /**
     * Para delimitar a consulta sempre com a quantidade passada(Top)
     */
    List<Restaurante> findTop2ByNomeContaining(String nome);

    /**
     * Para verificar quantos restaurantes determinada cozinha possui
     */
    int countByCozinhaId(Long cozinhaId);

}
