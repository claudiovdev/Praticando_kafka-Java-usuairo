package com.api.usuario.api.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioModelResponse {
    @Schema(example = "1")
    private Long id;
    @Schema(example = "Vinicius")
    private String nome;
    @Schema(example = "vinicius@gmail.com")
    private String email;
    @JsonProperty("telefones")
    private List<TelefoneModelResponse> telefoneModelResponse = new ArrayList<>();
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy'T'HH:mm:ss'Z'")
    private LocalDateTime dataCriacao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy'T'HH:mm:ss'Z'")
    private LocalDateTime dataAtualizacao;
    @Schema(example = "ativo")
    private String status;
    @Schema(example = "Endereco")
    private EnderecoModelResponse enderecoModelResponse;
}
