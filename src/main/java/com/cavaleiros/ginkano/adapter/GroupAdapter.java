package com.cavaleiros.ginkano.adapter;

import com.cavaleiros.ginkano.core.domain.dto.Group;
import com.cavaleiros.ginkano.core.domain.request.GroupRequest;
import com.cavaleiros.ginkano.core.domain.response.GroupResponse;
import com.cavaleiros.ginkano.core.entity.Escola;

public class GroupAdapter {

    public static Group toSchool(Escola escola){
        return Group.builder()
                .nome(escola.getNome())
                .token(escola.getToken())
                .endereco(escola.getEndereco())
                .build();
    }

    public static Escola toEscola(GroupRequest groupRequest){
        return Escola.builder()
                .nome(groupRequest.getNome())
                .endereco(groupRequest.getEndereco())
                .build();
    }

    public static Group fromGroupRequest(GroupRequest groupRequest) {
        return Group.builder()
                .nome(groupRequest.getNome())
                .endereco(groupRequest.getEndereco())
                .build();
    }


    public static GroupResponse toSchoolResponse(Escola escola){
        return GroupResponse.builder()
                .group(toSchool(escola))
                .build();
    }
}
