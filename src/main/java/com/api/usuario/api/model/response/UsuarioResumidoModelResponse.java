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
public class UsuarioResumidoModelResponse {

    @Schema(example = "1")
    private Long id;
    @Schema(example = "Vinicius")
    private String nome;
    @Schema(example = "21976005392")
    private String telefonePrincipal;
    @Schema(example = "ativo")
    private String status;
    @Schema(example = "Bairro: Campo Grande, Rua: Rua Vítor Alves, numero:1393")
    private String endereco;
}
