package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.dto.model.FormaPagamentoModel;
import com.algaworks.algafood.domain.model.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FormaPagamentoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public FormaPagamentoModel toModel(FormaPagamento formaPagamento){
        return modelMapper.map(formaPagamento, FormaPagamentoModel.class);
    }

    public List<FormaPagamentoModel> toCollectionModel(List<FormaPagamento> formasDePagamento) {
        return formasDePagamento.stream()
                .map(formas -> toModel(formas))
                .collect(Collectors.toList());
    }
}
