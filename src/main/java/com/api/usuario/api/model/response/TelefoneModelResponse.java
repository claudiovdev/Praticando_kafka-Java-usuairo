package com.api.usuario.api.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TelefoneModelResponse {
    @Schema(example = "21976005392")
    private String numero;
    @Schema(example = "Vinicius")
    private String titular;
    @Schema(example = "PRINCIPAL")
    private String tipoTelefone;
}
