package com.algaworks.algafood.api.disassembler;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.dto.input.CozinhaInput;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CozinhaInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Cozinha toDomainObject(CozinhaInput input) {
        return modelMapper.map(input, Cozinha.class);
    }

    public void copyToDomainObject(CozinhaInput input, Cozinha cozinha) {
        modelMapper.map(input, cozinha);
    }
}
