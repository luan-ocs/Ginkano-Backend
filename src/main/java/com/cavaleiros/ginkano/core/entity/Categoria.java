package com.cavaleiros.ginkano.core.entity;

import com.cavaleiros.ginkano.core.domain.constants.TipoEnum;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "categoria")
@Getter
public class Categoria {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    String descricao;

    @ManyToOne
    Tipo tipo;

    public Categoria(String descricao, Tipo tipo) {
        this.descricao = descricao;
        this.tipo = tipo;
    }
}
