package com.api.usuario.domain.exception;

public class UsuarioNaoEncontradoException extends EntidadeNaoEncontradaException{
    public UsuarioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public UsuarioNaoEncontradoException(Long usuarioId){
        super(String.format("Não foi possivel encontrar um usuario com Id: %s", usuarioId.toString()));
    }
}
