package com.cavaleiros.ginkano.usecase;

import com.cavaleiros.ginkano.core.domain.response.UserTokenResponse;
import com.cavaleiros.ginkano.exception.UsernameInvalidException;

public interface RegisterUserUsecase {

    UserTokenResponse execute(String username, String firstname, String lastname, Integer conditions, String ocupacao, String password) throws UsernameInvalidException;
}
