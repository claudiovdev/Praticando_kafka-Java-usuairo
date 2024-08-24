package com.api.usuario.domain.service;

import com.api.usuario.domain.enums.StatusUsuario;
import com.api.usuario.domain.exception.EntidadeEmUsoException;
import com.api.usuario.domain.exception.UsuarioNaoEncontradoException;
import com.api.usuario.domain.entity.Usuario;
import com.api.usuario.domain.repository.TelefoneRepository;
import com.api.usuario.domain.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final TelefoneRepository telefoneRepository;

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
}
