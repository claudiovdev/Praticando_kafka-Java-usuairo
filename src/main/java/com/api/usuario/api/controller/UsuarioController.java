package com.api.usuario.api.controller;


import com.api.usuario.api.mapper.UsuarioModelAssembler;
import com.api.usuario.api.model.request.UsuarioModelRequest;
import com.api.usuario.api.model.request.UsuarioUpdateModelRequest;
import com.api.usuario.api.model.response.UsuarioModelResponse;
import com.api.usuario.api.model.response.UsuarioResumidoModelResponse;
import com.api.usuario.api.openapi.controller.UsuarioControllerOpenApi;

import com.api.usuario.domain.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuarioController implements UsuarioControllerOpenApi {

    private final UsuarioService usuarioService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/cadastrar")
    public UsuarioModelResponse cadastrarUsuario(@RequestBody @Valid UsuarioModelRequest usuarioModelRequest) throws Exception {
        var usuario = UsuarioModelAssembler.toModel(usuarioModelRequest);
        usuario =  usuarioService.cadastrarUsuario(usuario);
        return UsuarioModelAssembler.toResponse(usuario);
    }

    @GetMapping
    public List<UsuarioResumidoModelResponse> listarUsuarios(){
        return UsuarioModelAssembler.toResumido(usuarioService.listarTodos());
    }

    @GetMapping("{usuarioId}")
    public UsuarioModelResponse buscarUsuarioPorId(@PathVariable Long usuarioId){
        return UsuarioModelAssembler.toResponse(usuarioService.buscarUsuarioPorId(usuarioId));
    }

    @DeleteMapping("{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarUsuarioPorId(@PathVariable Long usuarioId){
        usuarioService.deletarUsuario(usuarioId);
    }

    @PutMapping("{usuarioId}")
    public UsuarioModelResponse atualizarUsuario(@PathVariable Long usuarioId, @RequestBody @Valid UsuarioUpdateModelRequest usuarioUpdateModelRequest){
        var usuario = UsuarioModelAssembler.toModelFromUsuarioUpdate(usuarioUpdateModelRequest);
        return UsuarioModelAssembler.toResponse(usuarioService.atualizarUsuario(usuarioId, usuario));
    }

}
