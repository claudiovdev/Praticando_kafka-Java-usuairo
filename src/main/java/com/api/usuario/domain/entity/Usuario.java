package com.api.usuario.domain.entity;

import com.api.usuario.domain.enums.StatusUsuario;
import com.api.usuario.domain.enums.TipoTelefone;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioId;
    @Column(nullable = false)
    private String nome;
    @OneToMany(mappedBy = "usuario")
    private List<Telefone> telefones = new ArrayList<>();
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy'T'HH:mm:ss'Z'")
    private LocalDateTime dataCriacao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy'T'HH:mm:ss'Z'")
    private LocalDateTime dataAtualizacao;
    @Enumerated(value = EnumType.STRING)
    private StatusUsuario statusUsuario;
    @Embedded
    private Endereco endereco;
    public String retornaTelefonePrincipal(){
        return this.getTelefones().stream().filter(telefone -> telefone.getTipoTelefone().equals(TipoTelefone.PRINCIPAL))
                .findFirst().map(telefone -> telefone.getDdd().concat(telefone.getNumero())).orElseThrow(null);
    }
}
