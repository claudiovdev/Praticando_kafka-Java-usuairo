package com.api.usuario.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum TipoDoProblema {
    ERRO_DE_SISTEMA("/erro-sistema", "Erro no sistema"),
    PARAMETRO_INVALIDO("/parametro-invalido", "Parametro invalido"),
    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),
    ERRO_NEGOCIO("/erro-negocio","Violação da regra de negocio");


    private String title;
    private String uri;

    TipoDoProblema(String path, String title){
        this.uri = "https://gerenciadordecobrancas.com.br".concat(path);
        this.title = title;
    }

}
