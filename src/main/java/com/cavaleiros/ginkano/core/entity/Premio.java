package com.cavaleiros.ginkano.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name="premio")
@Getter
public class Premio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String nome;
    String descricao;

    public Premio(String nome, String descricao){
        this.nome= nome;
        this.descricao = descricao;
    }
}
