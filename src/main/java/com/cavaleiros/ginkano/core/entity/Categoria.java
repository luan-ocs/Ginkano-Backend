package com.cavaleiros.ginkano.core.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "categoria")
@Getter
public class Categoria {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    String descricao;

    @JoinColumn(name = "fk_tipo")
    @ManyToOne
    Tipo tipo;

    public Categoria(){}

    public Categoria(String descricao, Tipo tipo) {
        this.descricao = descricao;
        this.tipo = tipo;
    }
}
