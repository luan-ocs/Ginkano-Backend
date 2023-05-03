package com.cavaleiros.ginkano.adapter;

import com.cavaleiros.ginkano.core.domain.dto.ResponsibleUser;
import com.cavaleiros.ginkano.core.entity.Responsavel;

import java.text.Normalizer;
import java.time.LocalDate;

public class ResponsibleUserAdapter {

    public static ResponsibleUser toResponsibleUser(Responsavel responsavel){


        return ResponsibleUser.builder()
                .ocupacao(responsavel.getOcupacao())
                .password(responsavel.getPassword())
                .nome(responsavel.getNome())
                .conditions(true)
                .username(getUsename(responsavel))
                .confirm(LocalDate.now().toString())
                .firstname(getFirstname(responsavel))
                .lastname(getLastname(responsavel)).build();

    }

    private static String getLastname(Responsavel responsavel){
        String [] words = responsavel.getNome().split(" ");
        return words[words.length-1];
    }

    private static String getFirstname(Responsavel responsavel){
        return responsavel.getNome().split(" ")[0];
    }

    private static String getUsename(Responsavel responsavel){
        String str = (getFirstname(responsavel)+getLastname(responsavel)).toLowerCase();
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

}
