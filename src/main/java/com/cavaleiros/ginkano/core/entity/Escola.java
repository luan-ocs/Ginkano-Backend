package com.cavaleiros.ginkano.core.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "escola")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Escola {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    String nome;
    String token;
    String endereco;
    Integer ativo;

}
