package com.api.usuario.domain.entity;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {
    @Column(name = "endereco_cep")
    private String cep;
    @Column(name = "endereco_bairro")
    private String bairro;
    @Column(name = "endereco_rua")
    private String logradouro;
    @Column(name = "endereco_estado")
    private String localidade;
    @Column(name = "endereco_sigla")
    private String uf;
    @Column(name = "endereco_numero")
    private String numero;
}
