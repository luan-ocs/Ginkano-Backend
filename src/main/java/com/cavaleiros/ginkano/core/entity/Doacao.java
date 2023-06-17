package com.cavaleiros.ginkano.core.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "doacao")
public class Doacao {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    private String item;
    private String doador;
    private Double quantidade;
    private Long pontos;

    @ManyToOne
    @JoinColumn(name = "fk_escola")
    private Escola escola;
}
