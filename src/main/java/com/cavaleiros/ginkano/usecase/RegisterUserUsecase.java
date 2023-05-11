package com.cavaleiros.ginkano.usecase;

import com.cavaleiros.ginkano.core.domain.request.RegisterUser;
import com.cavaleiros.ginkano.core.domain.response.UserTokenResponse;
import com.cavaleiros.ginkano.exception.UsernameInvalidException;

public interface RegisterUserUsecase {

    UserTokenResponse execute(RegisterUser user) throws UsernameInvalidException;
}
