package com.cavaleiros.ginkano.usecase;

import com.cavaleiros.ginkano.core.domain.constants.Functions;
import com.cavaleiros.ginkano.core.domain.request.GroupRequest;
import com.cavaleiros.ginkano.core.domain.response.GroupAllResponse;
import com.cavaleiros.ginkano.core.domain.response.GroupResponse;
import com.cavaleiros.ginkano.exception.InvalidTokenException;

public interface GroupUsecase {

    GroupAllResponse execute(String auth) throws InvalidTokenException;

    GroupResponse execute(String token, String auth, Functions function) throws InvalidTokenException;

    GroupResponse execute(GroupRequest groupRequest, String auth, String token) throws InvalidTokenException;

    GroupResponse execute(GroupRequest groupRequest, String auth) throws InvalidTokenException;

    GroupResponse executeId(String token) throws InvalidTokenException;
}
