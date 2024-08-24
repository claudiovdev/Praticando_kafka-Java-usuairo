package com.api.usuario.api.exceptionhandler;


import com.api.usuario.domain.exception.EntidadeNaoEncontradaException;
import com.api.usuario.domain.exception.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler  {

    @Autowired
    private MessageSource messageSource;


    public static final String MSG_ERRO_GENERICO_USUARIO_FINAL = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se o problema persistir" +
            " entre em contato com o adminstrador do sistema";

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        TipoDoProblema tipoDoProblema = TipoDoProblema.ERRO_DE_SISTEMA;
        String detail = String.format("O recurso %s que você tentou acessar, é inexistente", ex.getRequestURL());

        Problema problem = createProbemBuilder(status, tipoDoProblema, detail).build();

        return handleExceptionInternal(ex, problem, headers,status,request);

    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        if (body == null){
            body = Problema.builder()
                    .timestemp(LocalDateTime.now())
                    .title(statusCode.toString())
                    .status(statusCode.value())
                    .userMessage(MSG_ERRO_GENERICO_USUARIO_FINAL)
                    .build();
        } else if (body instanceof String) {
            body = Problema.builder()
                    .timestemp(LocalDateTime.now())
                    .title((String) body)
                    .status(statusCode.value())
                    .userMessage(MSG_ERRO_GENERICO_USUARIO_FINAL)
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        return handleValidationInternal(ex, headers, status, request, ex.getBindingResult());
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, WebRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        TipoDoProblema tipoDoProblema = TipoDoProblema.ENTIDADE_NAO_ENCONTRADA;
        String detalhe = ex.getMessage();
        Problema problema = createProbemBuilder(status, tipoDoProblema, detalhe).build();
        return handleExceptionInternal(ex, problema, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?>handleNegocioExcetion(NegocioException ex, WebRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        TipoDoProblema tipoDoProblema = TipoDoProblema.ERRO_NEGOCIO;
        String detalhe = ex.getMessage();
        Problema problema = createProbemBuilder(status, tipoDoProblema, detalhe).build();
        return handleExceptionInternal(ex, problema, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private ResponseEntity<Object> handleValidationInternal(Exception ex, HttpHeaders headers, HttpStatusCode status, WebRequest request, BindingResult bindingResult) {
        String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";
        TipoDoProblema problemType = TipoDoProblema.PARAMETRO_INVALIDO;


        List<Problema.Field> problemFields = bindingResult.getFieldErrors().stream()
                .map(objectError -> {
                    String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
                    String name = objectError.getObjectName();

                    if (objectError instanceof FieldError){
                        name = ((FieldError) objectError).getField();
                    }
                    return Problema.Field.builder()
                            .nome(name)
                            .userMessage(message).build();
                }).collect(Collectors.toList());

        Problema problem = createProbemBuilder(status, problemType, detail)
                .userMessage("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.")
                .fields(problemFields)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private Problema.ProblemaBuilder createProbemBuilder(HttpStatusCode status, TipoDoProblema problemType, String detail){
        return Problema.builder()
                .status(status.value())
                .type(problemType.getUri())
                .title(problemType.getTitle())
                .detail(detail)
                .timestemp(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))));
    }


}
