package com.api.usuario.api.mapper;

import com.api.usuario.api.model.response.EnderecoModelResponse;
import com.api.usuario.domain.entity.Endereco;

public class EnderecoAssembler {
    public static EnderecoModelResponse toModel(Endereco endereco){
        var enderecoResponse = new EnderecoModelResponse();
        enderecoResponse.setCep(endereco.getCep());
        enderecoResponse.setBairro(endereco.getBairro());
        enderecoResponse.setRua(endereco.getLogradouro());
        enderecoResponse.setEstado(endereco.getLocalidade());
        enderecoResponse.setNumero(endereco.getNumero());
        return enderecoResponse;
    }
}
