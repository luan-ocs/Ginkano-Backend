package com.cavaleiros.ginkano.core.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class Group {

    String nome;
    String token;
    String endereco;
}