package com.cavaleiros.ginkano.adapter;

import com.cavaleiros.ginkano.core.domain.dto.ResponsibleUser;
import com.cavaleiros.ginkano.core.domain.request.RegisterUserRequest;
import com.cavaleiros.ginkano.core.domain.request.UserRequest;
import com.cavaleiros.ginkano.core.entity.Responsavel;

public class ResponsibleUserAdapter {

    public static ResponsibleUser toResponsibleUser(Responsavel responsavel){


        return ResponsibleUser.builder()
                .ocupacao(responsavel.getOcupacao())
                .username(responsavel.getUsername())
                .firstname(responsavel.getFirstname())
                .lastname(responsavel.getLastname()).build();
    }

    public static Responsavel createrResponsavel(RegisterUserRequest user){
        return new Responsavel(user.getUsername(), user.getFirstname(), user.getLastname(), user.getConditions(), user.getOcupacao(), user.getPassword(), 1);
    }

    public static Responsavel createrResponsavel(UserRequest user, Responsavel responsavel){
        responsavel.setFirstname(user.getFirstname());
        responsavel.setLastname(user.getLastname());
        responsavel.setConditions(user.getConditions());
        responsavel.setOcupacao(user.getOcupacao());
        responsavel.setUsername(user.getUsername());

        return responsavel;
    }
}
