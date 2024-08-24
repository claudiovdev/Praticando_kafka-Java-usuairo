package com.api.usuario.api.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoUpdateModelRequest {
    private String cep;
    private String estado;
    private String bairro;
    private String rua;
    private String numero;
}
