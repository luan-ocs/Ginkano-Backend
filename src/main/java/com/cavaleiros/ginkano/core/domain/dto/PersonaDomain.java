package com.cavaleiros.ginkano.core.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@SuperBuilder
public abstract class PersonaDomain implements Serializable {

    private static final long serialVersionUID = 5342498000143281552L;

    String username;
    String firstname;
    String lastname;
}
