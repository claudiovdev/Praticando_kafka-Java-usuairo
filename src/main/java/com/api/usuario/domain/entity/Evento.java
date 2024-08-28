package com.api.usuario.domain.entity;

import com.api.usuario.domain.model.DadosEvento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Evento {
    private String idTransacao;
    private DadosEvento dados;
}
