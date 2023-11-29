package com.algaworks.algafood.api.exceptionhandler;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

/**Esta anotação diz que dentro deste componente
 * podemos adicionar ExceptionHandlers, que as exceções
 * de todos os controladores do projeto serão tratadas
 * nesta classe*/
@ControllerAdvice
public class ApiExceptionHandler {

    /**
     * O método é chamado automataticamente pelo spring
     * passando a exception que foi lançada, e teremos
     * a chance de fazer o que quizermos, incluindo retornar
     * ResponseEntity customizando inclusive o corpo da resposta
     * http
     */
    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> tratarEntidadeNaoEncontradaException(
            EntidadeNaoEncontradaException e) {
        Problema problema = Problema.builder()
                .dataHora(LocalDateTime.now())
                .mensagem(e.getMessage()).build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(problema);
    }

    /**
     * A anotação ExceptionHandler, verifica também
     * a causa da exception também veja na linha 62
     * que passamos como parâmetro a causa (msg, causa)
     * -> throw new NegocioException(e.getMessage(), e);
     * */
    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> tratarNegocioException(
            NegocioException e) {
        /**Implementação de mensagem personalizada*/
        Problema problema = Problema.builder() //usando o builder do lombok
                .dataHora(LocalDateTime.now())// passando a data
                .mensagem(e.getMessage()).build();// passando a msg

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(problema);
    }

    /**
     * ExceptionHandler é legal, não só para customizar
     * mas para fazer tratamentos de exceptions que não
     * são implementdadas por nós*/
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<?> tratarHttpMediaTypeNotSupportedException(){
        Problema problema = Problema.builder() //usando o builder do lombok
                .dataHora(LocalDateTime.now())// passando a data
                .mensagem("O tipo de mídia não é aceito!").build();// passando a msg

        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .body(problema);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> tratarEntidadeEmUsoException(
            EntidadeEmUsoException e) {
        Problema problema = Problema.builder()
                .dataHora(LocalDateTime.now())
                .mensagem(e.getMessage()).build();

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(problema);
    }

}
