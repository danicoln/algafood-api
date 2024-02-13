package com.algaworks.algafood.api.disassembler;

import com.algaworks.algafood.api.dto.input.FormaPagamentoInput;
import com.algaworks.algafood.domain.model.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FormaPagamentoInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public FormaPagamento toDomainObject(FormaPagamentoInput input) {
        return modelMapper.map(input, FormaPagamento.class);
    }

    public void copyToDomainObject(FormaPagamentoInput input, FormaPagamento formaPagamento) {
        modelMapper.map(input, formaPagamento);
    }
}
