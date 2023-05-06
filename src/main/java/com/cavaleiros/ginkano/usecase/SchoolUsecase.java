package com.cavaleiros.ginkano.usecase;

import com.cavaleiros.ginkano.core.domain.response.SchoolAllResponse;
import com.cavaleiros.ginkano.core.domain.response.SchoolResponse;
import com.cavaleiros.ginkano.exception.InvalidTokenException;

public interface SchoolUsecase {

    SchoolAllResponse execute(String auth) throws InvalidTokenException;

    SchoolResponse execute(String token, String auth) throws InvalidTokenException;
}
