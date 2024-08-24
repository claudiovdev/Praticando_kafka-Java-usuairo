package com.api.usuario.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Schema(name = "Problema")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class Problema {
    @Schema(example = "400")
    private Integer status;
    @Schema(example = "https://controledeplantas.com.br/erro-sistema")
    private String type;
    @Schema(example = "Recurso não encontrado")
    private String title;
    @Schema(example = "O recurso /alecrin que você tentou acessar, é inexistente")
    private String detail;
    @Schema(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.")
    private String userMessage;
    @Schema(example = "18,07,2024,0,1,25")
    private LocalDateTime timestemp;
    @Schema(example = "Lista de objetos ou campos que geraram o erro (opcional)")
    private List<Field> fields;
    private List<Object> objects;

    @Getter
    @Builder
    public static class Field{
        @Schema(example = "nome")
        private String nome;

        @Schema(example = "O nome é obrigatório")
        private String userMessage;
    }
}
