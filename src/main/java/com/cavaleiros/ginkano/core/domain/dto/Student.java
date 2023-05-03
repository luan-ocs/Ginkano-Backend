package com.cavaleiros.ginkano.core.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@SuperBuilder
public class Student extends PersonaDomain{

    String matricula;
    LivingRoom sala;

    public Student(String nome, String username, String firstname, String lastname, String confirm, boolean conditions) {
        super(nome, username, firstname, lastname, confirm, conditions);
    }
}
