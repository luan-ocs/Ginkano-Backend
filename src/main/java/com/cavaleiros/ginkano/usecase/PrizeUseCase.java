package com.cavaleiros.ginkano.usecase;

import com.cavaleiros.ginkano.core.domain.response.PrizeAllResponse;
import com.cavaleiros.ginkano.core.domain.response.PrizeResponse;
import com.cavaleiros.ginkano.exception.InvalidTokenException;

public interface PrizeUseCase {
    PrizeAllResponse execute(String auth) throws InvalidTokenException;

    PrizeResponse execute(String token, String auth) throws InvalidTokenException;
}
