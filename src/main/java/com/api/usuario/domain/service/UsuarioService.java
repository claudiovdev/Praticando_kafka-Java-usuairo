package com.api.usuario.domain.service;

import com.api.usuario.domain.model.Evento;
import com.api.usuario.domain.enums.StatusUsuario;
import com.api.usuario.domain.exception.EntidadeEmUsoException;
import com.api.usuario.domain.exception.UsuarioNaoEncontradoException;
import com.api.usuario.domain.entity.Usuario;
import com.api.usuario.domain.model.DadosEvento;
import com.api.usuario.domain.model.Historico;
import com.api.usuario.domain.producer.UsuarioProducer;
import com.api.usuario.domain.repository.TelefoneRepository;
import com.api.usuario.domain.repository.UsuarioRepository;
import com.api.usuario.domain.utils.JsonUitls;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final TelefoneRepository telefoneRepository;
    private final UsuarioProducer usuarioProducer;
    private final JsonUitls jsonUitls;
    private final ObjectMapper objectMapper;

    private static final String MSG_USUARIO_EM_USO = "Usuario com id: %s não pode ser removido pois está em uso";

    @Transactional
    public Usuario cadastrarUsuario(Usuario usuario) throws Exception {
        usuario.setDataCriacao(LocalDateTime.now());
        usuario.setDataAtualizacao(LocalDateTime.now());
        usuario.setStatusUsuario(StatusUsuario.NOVO_CLIENTE);
        var numero = usuario.getEndereco().getNumero();
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        usuarioSalvo.getTelefones().forEach(telefone -> {
            telefone.setUsuario(usuarioSalvo);
            telefoneRepository.save(telefone);;
        });
        var evento = extratirDadosEvento(usuarioSalvo);

        usuarioProducer.enviarMensagem("notificacao-usuario", evento);
        return usuarioSalvo;
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarUsuarioPorId(Long usuarioId) {
        return usuarioRepository.findById(usuarioId).orElseThrow(()-> new UsuarioNaoEncontradoException(usuarioId));
    }
    public void deletarUsuario(Long usuarioId) {
        try{
            Usuario usuario = buscarUsuarioExistente(usuarioId);
            usuario.setStatusUsuario(StatusUsuario.INATIVO);
            usuarioRepository.save(usuario);
        }catch (DataIntegrityViolationException ex){
            throw new EntidadeEmUsoException(String.format(MSG_USUARIO_EM_USO, usuarioId));
        }catch (EmptyResultDataAccessException ex){
            throw new UsuarioNaoEncontradoException(usuarioId);
        }
    }

    @Transactional
    public Usuario atualizarUsuario(Long usuarioId, Usuario usuario) {
        var usuarioExistente = buscarUsuarioExistente(usuarioId);
        usuarioExistente.setNome(usuario.getNome());
        usuarioExistente.setDataAtualizacao(LocalDateTime.now());
        if (usuarioExistente.getTelefones().size() > 1){
            var primeiroTelefone = usuario.getTelefones().get(0);
            var segundoTelefone = usuario.getTelefones().get(1);

        }
        return usuarioExistente;
    }

    public Usuario buscarUsuarioExistente(Long id){
        return usuarioRepository.findById(id).orElseThrow(()-> new UsuarioNaoEncontradoException(id));
    }

    private Evento extratirDadosEvento(Usuario usuario){
        var dadosEvento = DadosEvento.builder()
                .id(usuario.getUsuarioId().toString())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .build();
        var historico = Historico.builder()
                .projeto("API-USUARIO")
                .status("SUCESSO")
                .message("Usuario cadastrado com sucesso")
                .dataDeCriacao(LocalDateTime.now())
                .build();
        return Evento.builder()
                .idTransacao(UUID.randomUUID().toString())
                .dados(dadosEvento)
                .historicos(Arrays.asList(historico))
                .build();
    }
}
