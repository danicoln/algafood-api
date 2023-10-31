package com.algaworks.algafood.notificacao;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Esta classe representa as configurações de propriedades,
 * informando o prefixo de parâmetro
 * tudo o que tiver "notificador.email",
 * será representado por esta classe
 * */
@Component
@ConfigurationProperties("notificador.email")
public class NotificadorProperties {

    /**
     * Host do servidor de email
     */
    private String hostServidor;
    /**
     * Porta do servidor de email
     */
    private Integer portaServidor = 30; // configuração padrão caso n tenha configurado as propriedades

    // Getters and Setters
    public String getHostServidor() {
        return hostServidor;
    }

    public void setHostServidor(String hostServidor) {
        this.hostServidor = hostServidor;
    }

    public Integer getPortaServidor() {
        return portaServidor;
    }

    public void setPortaServidor(Integer portaServidor) {
        this.portaServidor = portaServidor;
    }
}
