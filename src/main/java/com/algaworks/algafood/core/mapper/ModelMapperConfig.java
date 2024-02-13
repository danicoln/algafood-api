package com.algaworks.algafood.core.mapper;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.api.dto.model.RestauranteModel;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        /**11.16. Customização do mapeamento de propriedades com ModelMapper*/

        modelMapper.createTypeMap(Restaurante.class, RestauranteModel.class)
                .addMapping(Restaurante::getTaxaFrete, RestauranteModel::setPrecoFrete);

        return modelMapper;
    }
}
