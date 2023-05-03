package com.cavaleiros.ginkano.core.entity;

import lombok.AllArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "escola")
public class Escola {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    String nome;
    String endereco;

    public Escola(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
    }
}
