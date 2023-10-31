package com.algaworks.algafood.notificacao;

import com.algaworks.algafood.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
@Component
public class NotificadorEmail implements Notificador {

    @Autowired
    private NotificadorProperties properties;

    @Override
    public void notificar(Cliente cliente, String msg){
        System.out.println("Host " + properties.getHostServidor());
        System.out.println("Porta " + properties.getPortaServidor());

        System.out.printf("Notificando %s através do e-mail %s: %s \n",
                cliente.getNome(), cliente.getEmail(), msg);
    }
}
