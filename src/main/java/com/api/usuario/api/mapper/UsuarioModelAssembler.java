package com.api.usuario.api.mapper;

import com.api.usuario.api.model.request.UsuarioModelRequest;
import com.api.usuario.api.model.request.UsuarioUpdateModelRequest;
import com.api.usuario.api.model.response.TelefoneModelResponse;
import com.api.usuario.api.model.response.UsuarioModelResponse;
import com.api.usuario.api.model.response.UsuarioResumidoModelResponse;
import com.api.usuario.domain.entity.Endereco;
import com.api.usuario.domain.entity.Telefone;
import com.api.usuario.domain.entity.Usuario;
import com.api.usuario.domain.enums.TipoTelefone;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UsuarioModelAssembler {

    public static UsuarioModelResponse toResponse(Usuario usuario){
        var usuarioModelResponse = new UsuarioModelResponse();
        var telefonesModelResponse = new ArrayList<TelefoneModelResponse>();
        usuario.getTelefones().forEach(telefone -> {
            telefonesModelResponse.add(TelefoneModelAssembler.toResponse(telefone));
        });

        var endereco = EnderecoAssembler.toModel(usuario.getEndereco());

        usuarioModelResponse.setId(usuario.getUsuarioId());
        usuarioModelResponse.setTelefoneModelResponse(telefonesModelResponse);
        usuarioModelResponse.setNome(usuario.getNome());
        usuarioModelResponse.setEmail(usuario.getEmail());
        usuarioModelResponse.setDataCriacao(usuario.getDataCriacao());
        usuarioModelResponse.setDataAtualizacao(usuario.getDataAtualizacao());
        usuarioModelResponse.setEnderecoModelResponse(endereco);
        usuarioModelResponse.setStatus(usuario.getStatusUsuario().toString().toLowerCase());

        return usuarioModelResponse;
    }

    public static List<UsuarioResumidoModelResponse> toResumido(List<Usuario> usuarios){
        var listaDeUsuariosResumidos = new ArrayList<UsuarioResumidoModelResponse>();
        usuarios.forEach(usuario -> {
            var usuarioResumido = new UsuarioResumidoModelResponse();
            usuarioResumido.setId(usuario.getUsuarioId());
            usuarioResumido.setNome(usuario.getNome());
            usuarioResumido.setTelefonePrincipal(usuario.retornaTelefonePrincipal());
            usuarioResumido.setStatus(usuario.getStatusUsuario().toString().toLowerCase());
            usuarioResumido.setEndereco(String.format("Bairro: %s, Rua: %s, numero:%s",usuario.getEndereco().getBairro(), usuario.getEndereco().getRua(),usuario.getEndereco().getNumero()));

            listaDeUsuariosResumidos.add(usuarioResumido);
        });
        return listaDeUsuariosResumidos;
    }

    public static Usuario toModel(UsuarioModelRequest usuarioModelRequest){
        var usuario = new Usuario();
        var telefones = new ArrayList<Telefone>();
        var endereco = new Endereco();

        usuarioModelRequest.getTelefones().forEach(telefoneModelRequest -> {
            telefones.add(TelefoneModelAssembler.toModel(telefoneModelRequest));
        });

        endereco.setCep(usuarioModelRequest.getCep());
        endereco.setBairro(usuarioModelRequest.getBairro());
        endereco.setRua(usuarioModelRequest.getRua());
        endereco.setRua(usuarioModelRequest.getRua());
        endereco.setUf(usuarioModelRequest.getUf());
        endereco.setNumero(usuarioModelRequest.getNumero());

        usuario.setNome(usuarioModelRequest.getNome());
        usuario.setTelefones(telefones);
        usuario.setEndereco(endereco);
        usuario.setEmail(usuarioModelRequest.getEmail());
        return usuario;
    }

    public static Usuario toModelFromUsuarioUpdate(UsuarioUpdateModelRequest usuarioUpdateModelRequest){
        Usuario usuario = new Usuario();
        var telefone = new Telefone();
        var tipoTelefone = TipoTelefone.valueOf(usuarioUpdateModelRequest.getTelefoneUpdateModelRequest().getTipoTelefone());
        var endereco = new Endereco();
        usuario.setNome(usuarioUpdateModelRequest.getNome());
        telefone.setDdd(usuarioUpdateModelRequest.getTelefoneUpdateModelRequest().getDdd());
        telefone.setNumero(usuarioUpdateModelRequest.getTelefoneUpdateModelRequest().getNumero());
        telefone.setTipoTelefone(tipoTelefone);
        endereco.setCep(usuarioUpdateModelRequest.getEnderecoUpdateModelRequest().getCep());
        endereco.setEstado(usuarioUpdateModelRequest.getEnderecoUpdateModelRequest().getEstado());
        endereco.setRua(usuarioUpdateModelRequest.getEnderecoUpdateModelRequest().getRua());
        endereco.setBairro(usuarioUpdateModelRequest.getEnderecoUpdateModelRequest().getBairro());
        endereco.setNumero(usuarioUpdateModelRequest.getEnderecoUpdateModelRequest().getNumero());
        usuario.setEndereco(endereco);
        usuario.setTelefones(Arrays.asList(telefone));
        return usuario;
    }


}
