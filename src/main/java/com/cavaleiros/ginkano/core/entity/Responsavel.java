package com.cavaleiros.ginkano.core.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "responsavel")
public class Responsavel {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    String nome;
    String ocupacao;
    String password;

    public Responsavel(){}

    public Responsavel(Long id, String nome, String ocupacao, String password) {
        this.id = id;
        this.nome = nome;
        this.ocupacao = ocupacao;
        this.password = password;
    }
}
