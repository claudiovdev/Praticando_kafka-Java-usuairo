package com.api.usuario.domain.model;

import com.api.usuario.domain.model.DadosEvento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class Evento {
    private String idTransacao;
    private DadosEvento dados;
    private List<Historico> historicos;
}
