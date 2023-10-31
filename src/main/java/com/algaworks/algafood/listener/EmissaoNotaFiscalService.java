package com.algaworks.algafood.listener;

import com.algaworks.algafood.service.ClienteAtivadoEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EmissaoNotaFiscalService {

    @EventListener //este método é um ouvinte de evento
    public void clienteAtivadoListener(ClienteAtivadoEvent event){
        System.out.println("Emitindo NF para cliente " + event.getCliente().getNome());
    }
}