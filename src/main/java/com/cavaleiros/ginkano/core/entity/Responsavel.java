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
    String username;
    String firstname;
    String lastname;
    Integer conditions;
    String ocupacao;
    String password;

    public Responsavel(){}

    public Responsavel(String username, String firstname, String lastname, Integer conditions, String ocupacao, String password) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.conditions = conditions;
        this.ocupacao = ocupacao;
        this.password = password;
    }
}
