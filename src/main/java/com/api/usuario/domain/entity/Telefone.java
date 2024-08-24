package com.api.usuario.domain.entity;

import com.api.usuario.domain.enums.TipoTelefone;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Telefone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long telefoneId;
    @Column(length = 2, nullable = false)
    private String ddd;
    @Column(length = 9,nullable = false)
    private String numero;
    @Column(nullable = false)
    private String titular;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoTelefone tipoTelefone;
    @ManyToOne
    @JoinColumn(name = "usuarioId", nullable = false)
    private Usuario usuario;
    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCriacao;
    @Column(nullable = false)
    private LocalDateTime dataAtualizacao;

    @PrePersist
    public void prePersiste(){
        var data =LocalDateTime.now();
        this.dataCriacao = data;
        this.dataAtualizacao = data;
    }

    @PreUpdate
    public void preUpdate(){
        this.dataAtualizacao = LocalDateTime.now();
    }

    public void atualizaParaPrincipal(){
        this.setTipoTelefone(TipoTelefone.PRINCIPAL);
    }

    public void atualizaParaRecado(){
        this.setTipoTelefone(TipoTelefone.RECADO);
    }
}
