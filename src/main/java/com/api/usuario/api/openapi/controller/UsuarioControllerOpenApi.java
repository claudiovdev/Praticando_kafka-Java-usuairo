package com.api.usuario.api.openapi.controller;

import com.api.usuario.api.model.request.UsuarioModelRequest;
import com.api.usuario.api.model.request.UsuarioUpdateModelRequest;
import com.api.usuario.api.model.response.UsuarioModelResponse;
import com.api.usuario.api.model.response.UsuarioResumidoModelResponse;
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
public interface UsuarioControllerOpenApi {
    @Operation(summary = "Cadastrar usuario", description = "Cadastra um usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuario cadastrada com sucesso!!"),
            @ApiResponse(responseCode = "400", description = "Dados invalidos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemaBadRequest.class)))
    })
    UsuarioModelResponse cadastrarUsuario(UsuarioModelRequest usuarioModelRequest) throws Exception;

    @Operation(summary = "Listar usuarios", description = "Serviço criado para listar usuarios")
    List<UsuarioResumidoModelResponse> listarUsuarios();

    @Operation(summary = "Buscar usuario", description = "Serviço criado para buscar usuario por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados invalidos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemaBadRequest.class))),
            @ApiResponse(responseCode = "404", description = "Usuario não encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemaNotFound.class)))
    })
    UsuarioModelResponse buscarUsuarioPorId(@Parameter(example = "1") Long usuarioId);

    @Operation(summary = "Deletar usuario", description = "Serviço criado para deletar usuario por Id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "400", description = "Dados invalidos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemaBadRequest.class))),
            @ApiResponse(responseCode = "404", description = "Usuario não encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemaNotFound.class)))
    })
    void deletarUsuarioPorId(Long usuarioId);

    @Operation(summary = "Atualizar usuario", description = "Serviço criado para atuaizar usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario Atualizado"),
            @ApiResponse(responseCode = "400", description = "Dados invalidos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemaBadRequest.class))),
            @ApiResponse(responseCode = "404", description = "Usuario não encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemaNotFound.class)))
    })
    UsuarioModelResponse atualizarUsuario(@Parameter(example = "1") Long usuarioId, @Parameter(ref = "corpo") UsuarioUpdateModelRequest usuarioUpdateModelRequest);
}
