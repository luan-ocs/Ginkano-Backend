package com.cavaleiros.ginkano.adapter;

import com.cavaleiros.ginkano.core.domain.dto.Prize;
import com.cavaleiros.ginkano.core.domain.response.PrizeResponse;
import com.cavaleiros.ginkano.core.entity.Premio;

public class PrizeAdapter {
    public static Prize toPrize(Premio premio){
        return Prize.builder()
                .nome(premio.getNome())
                .descricao(premio.getDescricao())
                .build();
    }

    public static PrizeResponse toPrizeResponse(Premio premio){
        return PrizeResponse.builder()
                .prize(toPrize(premio))
                .build();
    }
}
