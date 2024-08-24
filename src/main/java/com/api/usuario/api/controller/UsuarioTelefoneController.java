package com.api.usuario.api.controller;

import com.api.usuario.api.mapper.TelefoneModelAssembler;
import com.api.usuario.api.model.request.TelefoneModelRequest;
import com.api.usuario.api.model.response.TelefoneModelResponse;
import com.api.usuario.api.openapi.controller.UsuarioTelefoneControllerOpenApi;
import com.api.usuario.domain.service.TelefoneService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("usuarios/{usuarioId}/telefones")
@RestController
@AllArgsConstructor
public class UsuarioTelefoneController implements UsuarioTelefoneControllerOpenApi {

    private final TelefoneService telefoneService;

    @PutMapping("{telefoneId}/atualizar")
    public TelefoneModelResponse atualizarTelefone(@PathVariable("usuarioId") Long usuarioId,
                                                   @PathVariable("telefoneId") Long telefoneId,
                                                   @RequestBody @Valid TelefoneModelRequest telefoneModelRequest){
        var teleofne = TelefoneModelAssembler.toModel(telefoneModelRequest);
        return TelefoneModelAssembler.toResponse(telefoneService.atualizarTelefone(usuarioId,telefoneId,teleofne));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{telefoneId}/excluir")
    public void deletarTelefone(@PathVariable("usuarioId") Long usuarioId,  @PathVariable("telefoneId") Long telefoneId){
        telefoneService.deletarTelefone(usuarioId, telefoneId);
    }

    @PostMapping
    public TelefoneModelResponse cadastrarTelefone(@PathVariable("usuarioId") Long usuarioId,@RequestBody @Valid TelefoneModelRequest telefoneModelRequest){
        var telefone = TelefoneModelAssembler.toModel(telefoneModelRequest);
        return TelefoneModelAssembler.toResponse(telefoneService.cadastrarTelefone(usuarioId, telefone));
    }

    @PutMapping("{telefonePrincipalId}/atualizaParaPrincipal/{telefoneRecadoId}/atualizaParaRecado")
    public List<TelefoneModelResponse> atualizaTelefoneRecadoParaPrincipal(@PathVariable("usuarioId") Long usuarioId,
                                                                           @PathVariable("telefonePrincipalId") Long telefonePrincipalId,
                                                                           @PathVariable("telefoneRecadoId") Long telefoneRecadoId){
        return TelefoneModelAssembler.toListModelResponse(telefoneService.atualizaTelefonePrincipalERecado(usuarioId,telefonePrincipalId, telefoneRecadoId));
    }

}
