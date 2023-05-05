package com.cavaleiros.ginkano.core.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class ResponsibleUser extends PersonaDomain{

    String ocupacao;

    public ResponsibleUser(String nome, String username, String firstname, String lastname) {
        super(username, firstname, lastname);
    }
}
