package com.cavaleiros.ginkano.adapter;

import com.cavaleiros.ginkano.core.domain.dto.School;
import com.cavaleiros.ginkano.core.domain.response.SchoolResponse;
import com.cavaleiros.ginkano.core.entity.Escola;

public class SchoolAdapter {

    public static School toSchool(Escola escola){
        return School.builder()
                .nome(escola.getNome())
                .token(escola.getToken())
                .endereco(escola.getEndereco())
                .build();
    }

    public static SchoolResponse toSchoolResponse(Escola escola){
        return SchoolResponse.builder()
                .school(toSchool(escola))
                .build();
    }
}
