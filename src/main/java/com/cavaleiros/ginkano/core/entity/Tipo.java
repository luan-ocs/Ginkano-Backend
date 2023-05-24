package com.cavaleiros.ginkano.core.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "tipo")
@NoArgsConstructor
public class Tipo {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    String descricao;

    public Tipo(String descricao) {
        this.descricao = descricao;
    }
}
