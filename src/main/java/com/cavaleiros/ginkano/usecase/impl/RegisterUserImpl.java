package com.cavaleiros.ginkano.usecase.impl;

import com.cavaleiros.ginkano.adapter.ResponsibleUserAdapter;
import com.cavaleiros.ginkano.config.JwtTokenUtil;
import com.cavaleiros.ginkano.core.domain.dto.ResponsibleUser;
import com.cavaleiros.ginkano.core.domain.request.RegisterUser;
import com.cavaleiros.ginkano.core.domain.response.UserTokenResponse;
import com.cavaleiros.ginkano.core.entity.Responsavel;
import com.cavaleiros.ginkano.exception.UsernameInvalidException;
import com.cavaleiros.ginkano.repository.RepositoryUser;
import com.cavaleiros.ginkano.usecase.RegisterUserUsecase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegisterUserImpl implements RegisterUserUsecase {

    private final RepositoryUser repositoryUser;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public UserTokenResponse execute(RegisterUser user) throws UsernameInvalidException {

        Responsavel checkUsername = repositoryUser.findResponsavelByUsername(user.getUsername());
        if(!ObjectUtils.isEmpty(checkUsername)){
            throw new UsernameInvalidException("Username já existe ou ocorreu um erro na persistência");
        }

        Responsavel responsavel = ResponsibleUserAdapter.createrResponsavel(user);
        repositoryUser.save(responsavel);

        ResponsibleUser responsibleUser = ResponsibleUserAdapter.toResponsibleUser(responsavel);
        return UserTokenResponse.builder()
                .data(responsibleUser)
                .jwtToken(jwtTokenUtil.generateToken(responsibleUser)).build();
    }
}
