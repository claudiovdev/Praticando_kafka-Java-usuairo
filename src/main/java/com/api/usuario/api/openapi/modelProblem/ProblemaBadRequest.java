package com.api.usuario.api.openapi.modelProblem;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

@Schema(name = "ProblemaBadRequest")
@Getter
public class ProblemaBadRequest {
    @Schema(example = "400")
    private Integer status;
    @Schema(example = "https://gerenciadordecobrancas.com.br/parametro-invalido")
    private String type;
    @Schema(example = "Parametro invalido")
    private String title;
    @Schema(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.")
    private String detail;
    @Schema(example = "18,07,2024,0,1,25")
    private LocalDateTime timestemp;
}
