package com.cavaleiros.ginkano.core.domain.dto;

import com.cavaleiros.ginkano.core.domain.constants.TipoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Category {

    String descricao;
    TipoEnum tipo;
}
