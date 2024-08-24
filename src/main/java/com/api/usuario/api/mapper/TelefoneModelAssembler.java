package com.api.usuario.api.mapper;

import com.api.usuario.api.model.request.TelefoneModelRequest;
import com.api.usuario.api.model.response.TelefoneModelResponse;

import com.api.usuario.domain.entity.Telefone;
import com.api.usuario.domain.enums.TipoTelefone;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

public class TelefoneModelAssembler {

    public static TelefoneModelResponse toResponse(Telefone telefone){
        var telefoneModelResponse = new TelefoneModelResponse();
        telefoneModelResponse.setNumero(telefone.getDdd().concat(telefone.getNumero()));
        telefoneModelResponse.setTitular(telefone.getTitular());
        telefoneModelResponse.setTipoTelefone(telefone.getTipoTelefone().name());
        return telefoneModelResponse;
    }

    public static Telefone toModel(TelefoneModelRequest telefoneModelRequest){
        var telefone = new Telefone();
        telefone.setDdd(telefoneModelRequest.getDdd());
        telefone.setNumero(telefoneModelRequest.getNumero());
        telefone.setTitular(telefoneModelRequest.getTitular());
        if (!ObjectUtils.isEmpty(telefoneModelRequest.getTipoTelefone())){
            if (TipoTelefone.PRINCIPAL.toString().equals(telefoneModelRequest.getTipoTelefone())){
                telefone.setTipoTelefone(TipoTelefone.PRINCIPAL);
            }else {
                telefone.setTipoTelefone(TipoTelefone.RECADO);
            }
        }
        return telefone;
    }

    public static List<TelefoneModelResponse> toListModelResponse(List<Telefone> telefones){
        return telefones.stream().map(telefone -> toResponse(telefone)).collect(Collectors.toList());
    }
}
