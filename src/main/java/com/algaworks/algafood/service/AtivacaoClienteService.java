package com.algaworks.algafood.service;

import com.algaworks.algafood.model.Cliente;
import com.algaworks.algafood.notificacao.NivelUrgencia;
import com.algaworks.algafood.notificacao.Notificador;
import com.algaworks.algafood.notificacao.TipoDoNotificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AtivacaoClienteService {

    @TipoDoNotificador(NivelUrgencia.SEM_URGENCIA) //anotação customizada
    @Autowired
    private Notificador notificador;

    public void ativar(Cliente cliente) {
        cliente.ativar();
        notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
    }
}
