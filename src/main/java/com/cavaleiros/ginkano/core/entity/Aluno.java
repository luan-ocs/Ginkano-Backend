package com.cavaleiros.ginkano.core.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name = "aluno")
@Getter
public class Aluno {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    String matricula;

    @ManyToOne
    Sala sala;

    public Aluno(String matricula, Sala sala) {
        this.matricula = matricula;
        this.sala = sala;
    }
}
