package com.api.usuario.domain.service;


import com.api.usuario.domain.enums.TipoTelefone;
import com.api.usuario.domain.exception.NegocioException;
import com.api.usuario.domain.exception.TelefoneNaoEncontradoException;
import com.api.usuario.domain.entity.Telefone;
import com.api.usuario.domain.repository.TelefoneRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TelefoneService {

    private final TelefoneRepository telefoneRepository;
    private final UsuarioService usuarioService;

    public Telefone atualizarTelefone(Long usuarioId, Long telefoneId, Telefone telefone) {
        var usuarioExistente = usuarioService.buscarUsuarioExistente(usuarioId);
        Telefone telefoneExistente = usuarioExistente.getTelefones().stream().filter(tel -> tel.getTelefoneId().equals(telefoneId)).findFirst().orElseThrow(
                () ->new TelefoneNaoEncontradoException(telefoneId));

        BeanUtils.copyProperties(telefone,telefoneExistente,"telefoneId");
        telefoneExistente.setUsuario(usuarioExistente);

        return telefoneRepository.save(telefoneExistente);
    }


    public void deletarTelefone(Long usuarioId, Long telefoneId) {
        var usuarioExistente = usuarioService.buscarUsuarioExistente(usuarioId);
        if (usuarioExistente.getTelefones().size() <= 1){
            throw new NegocioException("Usuario não pode ter menos que um telefone");
        }
        var telefoneExistente = usuarioExistente.getTelefones().stream().filter(
                telefone -> telefone.getTelefoneId().equals(telefoneId)).findFirst().orElseThrow(() -> new TelefoneNaoEncontradoException(telefoneId));

        if (telefoneExistente.getTipoTelefone().equals(TipoTelefone.PRINCIPAL)){
            throw new NegocioException("Não é possivel excluir o telefone principal, por favor defina outro telefone como principal antes");
        }

        telefoneRepository.delete(telefoneExistente);

    }

    public Telefone cadastrarTelefone(Long usuarioId, Telefone telefone) {
        var usuarioExistente = usuarioService.buscarUsuarioExistente(usuarioId);
        telefone.setUsuario(usuarioExistente);
        return telefoneRepository.save(telefone);
    }

    public List<Telefone> atualizaTelefonePrincipalERecado(Long usuarioId, Long telefonePrincipalId, Long telefoneRecadoId) {
        var usuarioExistente = usuarioService.buscarUsuarioExistente(usuarioId);
        var telefones = usuarioExistente.getTelefones();
        if (telefones.size() <= 1){
            throw new RuntimeException("O telefone possue apenas 1 telefone e não pode ter o status dele alterado");
        }

        var telefonePrincipal = telefones.stream()
                .filter(tel -> tel.getTelefoneId().equals(telefonePrincipalId))
                .findFirst().orElseThrow(() -> new TelefoneNaoEncontradoException(telefonePrincipalId));

        var telefoneRecado = telefones.stream()
                .filter(tel -> tel.getTelefoneId().equals(telefoneRecadoId))
                .findFirst()
                .orElseThrow(() -> new TelefoneNaoEncontradoException(telefoneRecadoId));

        if (telefonePrincipal.getTipoTelefone().equals(TipoTelefone.PRINCIPAL)){
            throw new NegocioException(String.format("O telefone %s já é um telefone principal.", telefonePrincipal.getTelefoneId()));
        }

        if (telefoneRecado.getTipoTelefone().equals(TipoTelefone.RECADO)){
            throw new NegocioException(String.format("O telefone %s já é um telefone de recado.", telefoneRecado.getTelefoneId()));
        }

        var telefonesAtualizados = new ArrayList<Telefone>();

        telefonePrincipal.atualizaParaPrincipal();
        telefoneRepository.save(telefonePrincipal);
        telefonesAtualizados.add(telefonePrincipal);

        telefoneRecado.atualizaParaRecado();
        telefoneRepository.save(telefoneRecado);
        telefonesAtualizados.add(telefoneRecado);

        return telefonesAtualizados;
    }
}
