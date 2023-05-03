package com.cavaleiros.ginkano.core.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@AllArgsConstructor
@SuperBuilder
public abstract class PersonaDomain {

    String nome;
    String username;
    String firstname;
    String lastname;
    String confirm;
    boolean conditions;
}
