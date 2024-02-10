package com.algaworks.algafood.api.disassembler;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.model.input.RestauranteInput;
import org.springframework.stereotype.Component;

@Component
public class RestauranteModelDisassembler {

    public Restaurante toDomainObject(RestauranteInput input){
        Restaurante restaurante = new Restaurante();
        restaurante.setNome(input.getNome());
        restaurante.setTaxaFrete(input.getTaxaFrete());

        Cozinha cozinha = new Cozinha();
        cozinha.setId(input.getCozinha().getId());

        restaurante.setCozinha(cozinha);

        return restaurante;
    }

}
