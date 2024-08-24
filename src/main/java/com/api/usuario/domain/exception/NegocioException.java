package com.api.usuario.domain.exception;

public class NegocioException extends RuntimeException{

    public NegocioException(String mensagem){
        super(mensagem);
    }
}
