package com.cavaleiros.ginkano.core.entity;

import javax.persistence.*;

@Entity
@Table(name = "tipo")
public class Tipo {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    String descricao;

    public Tipo(String descricao) {
        this.descricao = descricao;
    }
}
