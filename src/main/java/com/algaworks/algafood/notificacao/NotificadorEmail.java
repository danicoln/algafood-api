package com.algaworks.algafood.notificacao;

import com.algaworks.algafood.model.Cliente;
import org.springframework.stereotype.Component;

@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
@Component
public class NotificadorEmail implements Notificador {

    public NotificadorEmail(){
        System.out.println("Construtor chamado: NotificadorEmail");
    }

    @Override
    public void notificar(Cliente cliente, String msg){
        System.out.printf("Notificando %s através do e-mail %s: %s \n",
                cliente.getNome(), cliente.getEmail(), msg);
    }
}
