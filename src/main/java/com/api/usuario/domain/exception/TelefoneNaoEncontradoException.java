package com.api.usuario.domain.exception;

public class TelefoneNaoEncontradoException extends EntidadeEmUsoException{
    public TelefoneNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public TelefoneNaoEncontradoException(Long telefoneId){
        super(String.format("Não foi possivel localizar telefone com id: %s", telefoneId));
    }
}
