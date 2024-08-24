package com.api.usuario.api.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioUpdateModelRequest {
    @NotBlank
    @Schema(example = "Vinicius", required = true)
    private String nome;
    private String status;
    private TelefoneUpdateModelRequest telefoneUpdateModelRequest;
    private EnderecoUpdateModelRequest enderecoUpdateModelRequest;

}
