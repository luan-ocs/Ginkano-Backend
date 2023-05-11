package com.cavaleiros.ginkano.core.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUser {

    String username;
    String password;
    String firstname;
    String lastname;
    String ocupacao = "responsável";
    Integer conditions;
}
