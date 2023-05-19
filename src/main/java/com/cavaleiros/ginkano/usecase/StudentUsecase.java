package com.cavaleiros.ginkano.usecase;

import com.cavaleiros.ginkano.core.domain.response.StudentAllResponse;
import com.cavaleiros.ginkano.core.domain.response.StudentResponse;
import com.cavaleiros.ginkano.exception.InvalidTokenException;

public interface StudentUsecase {
    StudentAllResponse execute(String auth) throws InvalidTokenException;

    StudentResponse execute(String token, String auth)throws InvalidTokenException;
}