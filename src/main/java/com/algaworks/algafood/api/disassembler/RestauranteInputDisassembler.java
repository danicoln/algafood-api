package com.algaworks.algafood.api.disassembler;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.model.input.RestauranteInput;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestauranteInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Restaurante toDomainObject(RestauranteInput input){
        return modelMapper.map(input, Restaurante.class);
    }

    /**11.17. Mapeano para uma instância de destino.
     *
     * Converter para uma entidade, copia o input para restaurante*/

    public void copyToDomainObject(RestauranteInput input, Restaurante restaurante) {
        //para que não tenha problema sobre tentativa de alterar id da cozinha
        //precisa fazer uma nova instância.
        //Isso evita a exception: org.hibernate.HibernateException: identifier of an instance of com.algaworks.algafood.domain.model.Cozinha was altered from 5 to 7
        restaurante.setCozinha(new Cozinha());

        modelMapper.map(input, restaurante);
    }

}
