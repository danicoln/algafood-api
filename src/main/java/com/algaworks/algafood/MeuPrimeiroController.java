package com.algaworks.algafood;

import com.algaworks.algafood.model.Cliente;
import com.algaworks.algafood.service.AtivacaoClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MeuPrimeiroController {

    private AtivacaoClienteService service;

    public MeuPrimeiroController(AtivacaoClienteService service) {
        this.service = service;
        System.out.println("MeuPrimeiroController: " + service);
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        Cliente joao = new Cliente("João", "joao@xyz.com", "19988884444");
        service.ativar(joao);

        return "Olá Linconha!";
    }
}
