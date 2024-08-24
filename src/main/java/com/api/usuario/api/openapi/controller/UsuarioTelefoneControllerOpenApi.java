package com.api.usuario.api.openapi.controller;

import com.api.usuario.api.model.request.TelefoneModelRequest;
import com.api.usuario.api.model.response.TelefoneModelResponse;
import com.api.usuario.api.openapi.modelProblem.ProblemaBadRequest;
import com.api.usuario.api.openapi.modelProblem.ProblemaNotFound;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Usuarios", description = "Gerenciamento de Usuarios")
public interface UsuarioTelefoneControllerOpenApi {
    @Operation(summary = "atualizar telefone", description = "Serviço criado para atualizar telefone por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "telefone atualizado"),
            @ApiResponse(responseCode = "400", description = "Dados invalidos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemaBadRequest.class))),
            @ApiResponse(responseCode = "404", description = "Usuario não encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemaNotFound.class)))
    })
    TelefoneModelResponse atualizarTelefone(@Parameter(example = "1") Long usuarioId,
                                            @Parameter(example = "1")Long telefoneId,
                                            TelefoneModelRequest telefoneModelRequest);
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "telefone excluido"),
            @ApiResponse(responseCode = "400", description = "Dados invalidos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemaBadRequest.class))),
            @ApiResponse(responseCode = "404", description = "Usuario não encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemaNotFound.class)))
    })
    @Operation(summary = "excluir telefone", description = "Serviço criado para excluir telefone por Id")
    void deletarTelefone(@Parameter(example = "1") Long usuarioId,
                         @Parameter(example = "1")Long telefoneId);
    @Operation(summary = "Cadastrar telefone", description = "Cadastra um telefone")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "telefone cadastrada com sucesso!!"),
            @ApiResponse(responseCode = "400", description = "Dados invalidos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemaBadRequest.class))),
            @ApiResponse(responseCode = "404", description = "Usuario não encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemaNotFound.class)))
    })
    TelefoneModelResponse cadastrarTelefone(@Parameter(example = "1") Long usuarioId,TelefoneModelRequest telefoneModelRequest);

    @Operation(summary = "atualizar telefone", description = "Serviço criado para atualizar telefone para principal e recado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "telefone atualizado"),
            @ApiResponse(responseCode = "400", description = "Dados invalidos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemaBadRequest.class))),
            @ApiResponse(responseCode = "404", description = "Usuario não encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemaNotFound.class)))
    })
    List<TelefoneModelResponse> atualizaTelefoneRecadoParaPrincipal(@Parameter(example = "1") Long usuarioId,
                                                                    @Parameter(example = "2") Long telefonePrincipalId,
                                                                    @Parameter(example = "1") Long telefoneRecadoId);
}
