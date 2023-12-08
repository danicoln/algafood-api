package com.algaworks.algafood.api.exceptionhandler;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Esta anotação diz que dentro deste componente
 * podemos adicionar ExceptionHandlers, que as exceções
 * de todos os controladores do projeto serão tratadas
 * nesta classe
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private String detalhe;
    private ProblemType problemType;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request){
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ProblemType problemType = ProblemType.ERRO_DE_SISTEMA;
        String detail = "Ocorreu um erro interno inesperado no sistema. " +
                "Tente novamente e se o problema persistir, entre em " +
                "contato com o administrador do sistema.";

//        Importante colocar o printStackTrace (pelo menos por enquanto, que não estamos
//         fazendo logging) para mostrar a stacktrace no console
//         Se não fizer isso, você não vai ver a stacktrace de exceptions que seriam importantes
//         para você durante, especialmente na fase de desenvolvimento
        ex.printStackTrace();

        Problem problem = createProblemBuilder(status, problemType, detail).build();
        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (ex instanceof MethodArgumentTypeMismatchException){
            return handleMethodArgumentTypeMismatch((MethodArgumentTypeMismatchException) ex, headers, status, request);
        }
        return super.handleTypeMismatch(ex, headers, status, request);
    }

    private ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ProblemType problemType = ProblemType.PARAMETRO_INVALIDO;
        String detail = String.format(
                "O parâmetro de URL ‘%s’ recebeu o valor '%s', que é de um tipo inválido. " +
                        "Corrija e informe um valor compatível com o tipo %s. ", ex.getName(),
                ex.getValue(), ex.getRequiredType().getSimpleName());

        Problem problem = createProblemBuilder(status, problemType, detail).build();
        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
        String detail = String.format(
                "O recurso '%s', que você tentou acessar, é inexistente." , ex.getRequestURL());

        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Throwable rootCouse = rootCause(ex); // passei o método que eu criei

        if (rootCouse instanceof InvalidFormatException) {
            return handleInvalidFormat((InvalidFormatException) rootCouse, headers, status, request);

        } else if (rootCouse instanceof IgnoredPropertyException) {
            return handlePropertyBinding((IgnoredPropertyException) rootCouse, headers, status, request);

        } else if (rootCouse instanceof UnrecognizedPropertyException) {
            return handlePropertyBinding((UnrecognizedPropertyException) rootCouse, headers, status, request);
        }

        ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
        String detail = "O corpo da requisição está inválido. Verifique erro de sintaxe.";

        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    //método criado por mim

    private Throwable rootCause(Exception ex) {
        //ExceptionUtils do pacote commons.lang que adicionamos no pom.xml
        //Com esse cara, pegamos a causa raiz, indo em toda a pilha de
        //exceções (toda stack).
        return ExceptionUtils.getRootCause(ex);
    }

    private ResponseEntity<Object> handlePropertyBinding(
            PropertyBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String path = joinPath(ex.getPath());

        if (ex instanceof IgnoredPropertyException) {

            ProblemType problemType = ProblemType.PROPRIEDADE_IGNORADA;
            String detail = String.format(
                    "A propriedade '%s' está habilitada para ser ignorada " +
                            "na entidade atual", path);
            adicionaDetailAndProblemType(detail, problemType);

        } else if (ex instanceof UnrecognizedPropertyException) {

            ProblemType problemType = ProblemType.PROPRIEDADE_NAO_EXISTE_NA_ENTIDADE;
            String detail = String.format(
                    "A propriedade '%s' não existe " +
                            "na entidade atual", path);
            adicionaDetailAndProblemType(detail, problemType);
        }
        Problem problem = createProblemBuilder(status, getProblemType(), getDetalhe()).build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private void adicionaDetailAndProblemType(String detail, ProblemType problemType) {
        setProblemType(problemType);
        setDetalhe(detail);
    }

    private String joinPath(List<JsonMappingException.Reference> references) {
        return references.stream()
                .map(ref -> ref.getFieldName())
                .collect(Collectors.joining("."));
    }

    private void setProblemType(ProblemType problemType) {
        this.problemType = problemType;
    }

    private ProblemType getProblemType() {
        return problemType;
    }

    private String getDetalhe() {
        return this.detalhe;
    }

    private void setDetalhe(String detail) {
        this.detalhe = detail;
    }


    // método handle criado
    private ResponseEntity<Object> handleInvalidFormat(
            InvalidFormatException ex, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {

        /*
         * O método .getPath() retorna uma List<Reference>
         * fazemos um foreach para pegar cada instância de reference
         * e imprimimos no console com o .getFieldName()*/
        //ex.getPath().forEach(ref -> System.out.println(ref.getFieldName()));

        String path = joinPath(ex.getPath());

        ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
        String detail = String.format("A propriedade '%s' recebeu o valor '%s', " +
                "que é de um tipo inválido. Corrija e informe um valor compatível " +
                "com o tipo %s.", path, ex.getValue(), ex.getTargetType().getSimpleName());

        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

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
        ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
        String detail = ex.getMessage();

        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
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

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.ERRO_NEGOCIO;
        String detail = ex.getMessage();

        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(
                ex, problem, new HttpHeaders(),
                status, request);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handleEntidadeEmUsoException(
            EntidadeEmUsoException ex, WebRequest request) {

        HttpStatus status = HttpStatus.CONFLICT;
        ProblemType problemType = ProblemType.ENTIDADE_EM_USO;
        String detail = ex.getMessage();

        Problem problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
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
