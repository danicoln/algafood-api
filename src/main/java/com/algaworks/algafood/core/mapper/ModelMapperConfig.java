package com.algaworks.algafood.core.mapper;

import com.algaworks.algafood.api.dto.model.EnderecoModel;
import com.algaworks.algafood.domain.model.Endereco;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

//        /**11.16. Customização do mapeamento de propriedades com ModelMapper*/
//        modelMapper.createTypeMap(Restaurante.class, RestauranteModel.class)
//                .addMapping(Restaurante::getTaxaFrete, RestauranteModel::setPrecoFrete);

        var enderecoToEnderecoModelTypeMap = modelMapper.createTypeMap(Endereco.class, EnderecoModel.class);
        enderecoToEnderecoModelTypeMap.<String>addMapping(
                enderecoSource -> enderecoSource.getCidade().getEstado().getNome(),
                (enderecoModelDestino, valor) -> enderecoModelDestino.getCidade().setEstado(valor));
        return modelMapper;
    }
}
