package com.cavaleiros.ginkano.core.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "doacao")
public class Doacao {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    String descricao;
    Double quantItens;

    @ManyToOne
    Aluno aluno;
    @ManyToOne
    Responsavel responsavel;

    @ManyToOne
    Produto produto;

    public Doacao(String descricao, Double quantItens, Aluno aluno, Responsavel responsavel, Produto produto) {
        this.descricao = descricao;
        this.quantItens = quantItens;
        this.aluno = aluno;
        this.responsavel = responsavel;
        this.produto = produto;
    }
}
