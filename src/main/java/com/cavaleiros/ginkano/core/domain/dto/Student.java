package com.cavaleiros.ginkano.core.domain.dto;

import com.cavaleiros.ginkano.core.entity.Sala;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@SuperBuilder
public class Student extends PersonaDomain{

    String matricula;
    Sala sala;

    public Student(String nome, String username, String firstname, String lastname, String confirm, boolean conditions) {
        super(username, firstname, lastname);
    }
}
