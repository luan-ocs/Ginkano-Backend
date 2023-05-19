package com.cavaleiros.ginkano.usecase;

import com.cavaleiros.ginkano.core.domain.constants.Functions;
import com.cavaleiros.ginkano.core.domain.request.RegisterUserRequest;
import com.cavaleiros.ginkano.core.domain.request.UserRequest;
import com.cavaleiros.ginkano.core.domain.response.UserResponse;
import com.cavaleiros.ginkano.core.domain.response.UserTokenResponse;
import com.cavaleiros.ginkano.exception.InvalidTokenException;
import com.cavaleiros.ginkano.exception.UseCaseException;
import com.cavaleiros.ginkano.exception.UsernameInvalidException;

public interface UserUsecase {

    UserResponse execute(String auth, Functions functions) throws InvalidTokenException;

    UserTokenResponse execute(String auth, UserRequest registerUserRequest) throws InvalidTokenException, UsernameInvalidException, UseCaseException;
}
