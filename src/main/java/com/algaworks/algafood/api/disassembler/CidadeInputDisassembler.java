package com.algaworks.algafood.api.disassembler;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.model.dto.input.CidadeInput;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CidadeInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Cidade toDomainObject(CidadeInput input) {
        return modelMapper.map(input, Cidade.class);
    }

    public void copyToDomainObject(CidadeInput input, Cidade cidade) {
        cidade.setEstado(new Estado());
        modelMapper.map(input, cidade);
    }
}
