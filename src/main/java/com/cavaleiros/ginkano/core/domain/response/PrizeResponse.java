package com.cavaleiros.ginkano.core.domain.response;

import com.cavaleiros.ginkano.core.domain.dto.Prize;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PrizeResponse implements BodyResponse{
    
    Prize prize;
}
