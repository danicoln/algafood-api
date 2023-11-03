package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@Controller
//@ResponseBody // as respostas dos métodos, devem ser enviadas como resposta da requisição http
@RestController // É um controllador e tem um @ResponseBody
@RequestMapping(value = "/cozinhas") //modelagem REST APIs
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE) // o "produces" é para especificar que o método produz apenas um formato de conteúdo, neste caso, o formato JSON
    public List<Cozinha> listarJson(){
        System.out.println("Listar Json");
        return cozinhaRepository.listar();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE) // o "produces" é para especificar que o método produz apenas um formato de conteúdo, neste caso, o formato JSON
    public List<Cozinha> listarXml(){
        System.out.println("Listar Xml");
        return cozinhaRepository.listar();
    }

}
