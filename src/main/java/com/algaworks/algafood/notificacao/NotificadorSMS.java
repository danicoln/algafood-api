package com.algaworks.algafood.notificacao;

import com.algaworks.algafood.model.Cliente;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@TipoDoNotificador(NivelUrgencia.URGENTE)
@Component
public class NotificadorSMS implements Notificador {

    public NotificadorSMS(){
        System.out.println("Construtor chamado: NotificadorSMS");
    }

    @Override
    public void notificar(Cliente cliente, String msg){
        System.out.printf("Notificando %s por SMS através do telefone %s: %s \n",
                cliente.getNome(), cliente.getTelefone(), msg);
    }
}
