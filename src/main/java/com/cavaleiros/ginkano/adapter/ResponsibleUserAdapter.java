package com.cavaleiros.ginkano.adapter;

import com.cavaleiros.ginkano.core.domain.dto.ResponsibleUser;
import com.cavaleiros.ginkano.core.entity.Responsavel;

import java.text.Normalizer;

public class ResponsibleUserAdapter {

    public static ResponsibleUser toResponsibleUser(Responsavel responsavel){


        return ResponsibleUser.builder()
                .ocupacao(responsavel.getOcupacao())
                .username(responsavel.getUsername())
                .firstname(responsavel.getFirstname())
                .lastname(responsavel.getLastname()).build();
    }

    public static Responsavel createrResponsavel(String username, String firstname, String lastname, Integer conditions, String ocupacao, String password){
        return new Responsavel(username, firstname, lastname, conditions, ocupacao, password);
    }
}
