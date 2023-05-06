package com.cavaleiros.ginkano.usecase;

import com.cavaleiros.ginkano.core.domain.response.UserTokenResponse;
import com.cavaleiros.ginkano.exception.InvalidPasswordException;
import com.cavaleiros.ginkano.exception.UseCaseException;

public interface AuthUsecase {

    UserTokenResponse execute(String username, String password) throws UseCaseException, InvalidPasswordException;
}
