package com.algaworks.algafood.api.disassembler;

import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.model.input.EstadoInput;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EstadoInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Estado toDomainObject(EstadoInput input) {
        return modelMapper.map(input, Estado.class);
    }

    public void copyDomainObject(EstadoInput input, Estado estado) {
        modelMapper.map(input, estado);
    }
}
