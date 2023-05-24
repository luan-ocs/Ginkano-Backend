package com.cavaleiros.ginkano.core.domain.dto;

import com.cavaleiros.ginkano.core.domain.constants.TipoEnum;
import com.cavaleiros.ginkano.core.entity.Tipo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class Category {

    String descricao;
    Type type;
}
