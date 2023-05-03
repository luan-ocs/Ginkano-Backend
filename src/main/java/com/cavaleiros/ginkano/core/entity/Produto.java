package com.cavaleiros.ginkano.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    String descricao;
    Double ptProduto;
    Double bonus;

    @ManyToOne
    Categoria categoria;

    public Produto(String descricao, Double ptProduto, Double bonus, Categoria categoria) {
        this.descricao = descricao;
        this.ptProduto = ptProduto;
        this.bonus = bonus;
        this.categoria = categoria;
    }
}
