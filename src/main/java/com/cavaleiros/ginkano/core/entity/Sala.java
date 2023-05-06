package com.cavaleiros.ginkano.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sala")
public class Sala {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    String descricao;
    String numSala;

    public Sala(String descricao, String numSala) {
        this.descricao = descricao;
        this.numSala = numSala;
    }
}
