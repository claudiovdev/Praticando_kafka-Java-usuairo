package com.api.usuario.api.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TelefoneUpdateModelRequest {
    private String ddd;
    private String numero;
    private String tipoTelefone;
}
