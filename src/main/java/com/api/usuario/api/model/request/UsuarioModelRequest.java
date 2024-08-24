package com.api.usuario.api.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioModelRequest {
    @NotBlank
    @Schema(example = "Vinicius", required = true)
    private String nome;
    @NotBlank
    @Schema(example = "23080000", required = true)
    private String cep;
    @NotBlank
    @Schema(example = "2319", required = true)
    private String numero;
    @Valid
    private List<TelefoneModelRequest> telefones;
}
