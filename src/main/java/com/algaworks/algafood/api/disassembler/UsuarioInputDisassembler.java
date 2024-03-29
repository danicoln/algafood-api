package com.algaworks.algafood.api.disassembler;

import com.algaworks.algafood.api.dto.input.UsuarioComSenhaInput;
import com.algaworks.algafood.api.dto.input.UsuarioInput;
import com.algaworks.algafood.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Usuario toDomainObject(UsuarioComSenhaInput input) {
        return modelMapper.map(input, Usuario.class);
    }

    public void copyToDomainObject(UsuarioInput input, Usuario usuario) {
        modelMapper.map(input, usuario);
    }
}
