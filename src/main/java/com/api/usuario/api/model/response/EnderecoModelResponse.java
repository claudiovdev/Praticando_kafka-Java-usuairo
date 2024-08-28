package com.api.usuario.api.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoModelResponse {
    private String cep;
    private String bairro;
    private String rua;
    private String estado;
    private String uf;
    private String numero;
}
