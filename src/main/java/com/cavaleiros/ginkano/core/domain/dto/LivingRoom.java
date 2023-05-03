package com.cavaleiros.ginkano.core.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
@AllArgsConstructor
public class LivingRoom {

    String descricao;
    String numSala;
}
