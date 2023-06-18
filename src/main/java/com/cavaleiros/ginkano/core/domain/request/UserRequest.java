package com.cavaleiros.ginkano.core.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    String username;
    String firstname;
    String lastname;
    String ocupacao = "";
    Integer conditions = 1;
}
