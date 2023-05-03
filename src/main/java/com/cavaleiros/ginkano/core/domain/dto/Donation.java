package com.cavaleiros.ginkano.core.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Donation {

    String descricao;
    Double quantItens;
    Student aluno;
    ResponsibleUser responsavel;
    List<Product> produtos;
}
