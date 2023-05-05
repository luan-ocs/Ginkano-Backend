package com.cavaleiros.ginkano.usecase.impl;

import com.cavaleiros.ginkano.adapter.ResponsibleUserAdapter;
import com.cavaleiros.ginkano.config.JwtTokenUtil;
import com.cavaleiros.ginkano.core.domain.response.UserResponse;
import com.cavaleiros.ginkano.core.domain.response.UserTokenResponse;
import com.cavaleiros.ginkano.core.entity.Responsavel;
import com.cavaleiros.ginkano.exception.InvalidTokenException;
import com.cavaleiros.ginkano.repository.RepositoryUser;
import com.cavaleiros.ginkano.usecase.UserUsecase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserUsecaseImpl implements UserUsecase {

    private final JwtTokenUtil jwtTokenUtil;
    private final RepositoryUser repositoryUser;

    @Override
    public UserResponse execute(String auth) throws InvalidTokenException {

        Date expirationDate = jwtTokenUtil.getExpirationDateFromToken(auth);
        if(expirationDate.before(Date.from(Instant.now()))){
            throw new InvalidTokenException("Token expirado ou invalido");
        }

        String usernameAuth = jwtTokenUtil.getUsernameFromToken(auth);
        Responsavel responsavel = repositoryUser.findResponsavelByUsername(usernameAuth);



        return UserResponse.builder()
                .data(ResponsibleUserAdapter.toResponsibleUser(responsavel))
                .date(LocalDate.now()).build();
    }
}
