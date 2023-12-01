package com.algaworks.algafood.api.exceptionhandler;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Esta anotação diz que dentro deste componente
 * podemos adicionar ExceptionHandlers, que as exceções
 * de todos os controladores do projeto serão tratadas
 * nesta classe
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * O método é chamado automataticamente pelo spring
     * passando a exception que foi lançada, e teremos
     * a chance de fazer o que quizermos, incluindo retornar
     * ResponseEntity customizando inclusive o corpo da resposta
     * http
     */
    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontradaException(
            EntidadeNaoEncontradaException ex, WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.ENTIDADE_NAO_ENCONTRADA;
        String detail = ex.getMessage();

        Problem problem = createProblemBuilder(
                status, problemType, detail)
                .build();

//        Problem problem = Problem.builder()
//                .status(status.value())
//                .type("https://algafood.com.br/entidade-nao-encontrada")
//                .title("Entidade não encontrada")
//                .detail(detail)
//                .build();
        return handleExceptionInternal(
                ex, problem, new HttpHeaders(),
                status, request
        );

    }

    /**
     * A anotação ExceptionHandler, verifica também
     * a causa da exception também veja na linha 62
     * que passamos como parâmetro a causa (msg, causa)
     * -> throw new NegocioException(e.getMessage(), e);
     */
    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleNegocioException(
            NegocioException ex, WebRequest request) {

        return handleExceptionInternal(
                ex, ex.getMessage(), new HttpHeaders(),
                HttpStatus.NOT_FOUND, request
        );
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handleEntidadeEmUsoException(
            EntidadeEmUsoException ex, WebRequest request) {

        return handleExceptionInternal(
                ex, ex.getMessage(), new HttpHeaders(),
                HttpStatus.NOT_FOUND, request
        );
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
                                                             HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (body == null) {
            //instanciando um problema
            body = Problem.builder()
                    .title(status.getReasonPhrase()) // vem uma pequena descrição do status que está sendo retornado na resposta
                    .status(status.value()) // pega o código do status
                    .build();
        } else if (body instanceof String) {
            body = Problem.builder()
                    .title((String) body) // vem uma pequena descrição do status que está sendo retornado na resposta
                    .status(status.value())
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private Problem.ProblemBuilder createProblemBuilder(HttpStatus status,
                                                        ProblemType problemType, String detail) {
        return Problem.builder()
                .status(status.value())
                .type(problemType.getUri())
                .title(problemType.getTitle())
                .detail(detail);
    }
}
