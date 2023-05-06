package com.cavaleiros.ginkano.core.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "escola")
@Getter
public class Escola {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    String nome;
    String token;
    String endereco;

    public Escola() {}
    public Escola(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
    }
}
