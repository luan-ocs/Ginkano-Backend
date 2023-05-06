package com.cavaleiros.ginkano.usecase;

import com.cavaleiros.ginkano.core.domain.response.UserResponse;
import com.cavaleiros.ginkano.core.domain.response.UserTokenResponse;
import com.cavaleiros.ginkano.exception.InvalidPasswordException;
import com.cavaleiros.ginkano.exception.InvalidTokenException;
import com.cavaleiros.ginkano.exception.UseCaseException;

public interface UserUsecase {

    UserResponse execute(String auth) throws InvalidTokenException;
}
