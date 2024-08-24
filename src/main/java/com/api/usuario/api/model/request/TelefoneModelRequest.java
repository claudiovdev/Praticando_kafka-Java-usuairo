package com.api.usuario.api.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TelefoneModelRequest {
    @NotBlank
    @Schema(example = "21")
    @Size(min = 2, max = 2)
    private String ddd;
    @NotBlank
    @Schema(example = "976005392")
    @Size(min = 9, max = 9)
    private String numero;
    @NotBlank
    @Schema(example = "Vinicius")
    private String titular;
    @NotBlank
    @Schema(example = "PRINCIPAL")
    private String tipoTelefone;
}
