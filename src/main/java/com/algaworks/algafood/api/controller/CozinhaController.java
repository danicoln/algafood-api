package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.model.CozinhasXmlWrapper;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
//@ResponseBody // as respostas dos métodos, devem ser enviadas como resposta da requisição http
@RestController // É um controllador e tem um @ResponseBody
@RequestMapping(value = "/cozinhas") //modelagem REST APIs
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @GetMapping
    public List<Cozinha> listar() {
        return cozinhaRepository.listar();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CozinhasXmlWrapper listarXml() {
        return new CozinhasXmlWrapper(cozinhaRepository.listar());
    }

    @GetMapping("/{cozinhaId}") //path variable
    public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId) {
        Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);

        if (cozinha != null) {
            return ResponseEntity.ok(cozinha);
        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // para que o status seja o 201
    public Cozinha adicionar(@RequestBody Cozinha cozinha) {
        return cozinhaRepository.salvar(cozinha); // é interessante retornar a representação do que é criado
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId,
                                             @RequestBody Cozinha cozinha){
        Cozinha cozinhaPersistida = cozinhaRepository.buscar(cozinhaId);

        if(cozinhaPersistida != null){

//        cozinhaPersistida.setNome(cozinha.getNome());
            BeanUtils.copyProperties(cozinha, cozinhaPersistida, "id");
            /**O método copyProperties de BeanUtils do springfamework faz
             * o mesmo que a linha anterior (.setNome).
             * Ele copia os dados do primeiro paramentro
             * e salva no segundo paramentro, o terceiro parametro passamos
             * uma propriedade que não queremos que seja alterada, no caso o id
             * (precisa ser como string).
             * Bom para quando temos mtas propriedades*/
            cozinhaRepository.salvar(cozinhaPersistida);

            return ResponseEntity.ok(cozinhaPersistida);
        }

        return ResponseEntity.notFound().build();

    }

}
