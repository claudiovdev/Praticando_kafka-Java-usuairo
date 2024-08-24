package com.api.usuario.api.openapi.modelProblem;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

@Schema(name = "ProblemaNotFound")
@Getter
public class ProblemaNotFound {
    @Schema(example = "404")
    private Integer status;
    @Schema(example = "https://gerenciadordecobrancas.com.br/entidade-nao-encontrada")
    private String type;
    @Schema(example = "/entidade-nao-encontrada")
    private String title;
    @Schema(example = "Não foi possivel encontrar uma entidade com id: 1")
    private String detail;
    @Schema(example = "18,07,2024,0,1,25")
    private LocalDateTime timestemp;
}
