package com.cavaleiros.ginkano.usecase.impl;

import com.cavaleiros.ginkano.adapter.ResponsibleUserAdapter;
import com.cavaleiros.ginkano.core.domain.constants.Functions;
import com.cavaleiros.ginkano.core.domain.request.RegisterUserRequest;
import com.cavaleiros.ginkano.core.domain.request.UserRequest;
import com.cavaleiros.ginkano.core.domain.response.UserResponse;
import com.cavaleiros.ginkano.core.domain.response.UserTokenResponse;
import com.cavaleiros.ginkano.core.entity.Responsavel;
import com.cavaleiros.ginkano.exception.InvalidTokenException;
import com.cavaleiros.ginkano.exception.UseCaseException;
import com.cavaleiros.ginkano.exception.UsernameInvalidException;
import com.cavaleiros.ginkano.repository.RepositoryUser;
import com.cavaleiros.ginkano.usecase.UserUsecase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserUsecaseImpl extends BaseUsecase implements UserUsecase {

    private final RepositoryUser repositoryUser;

    @Override
    public UserResponse execute(String auth, Functions functions) throws InvalidTokenException {
        validatedToken(auth);

        String usernameAuth;
        Responsavel responsavel;

        switch (functions){
            case DELETE:
                usernameAuth = jwtTokenUtil.getUsernameFromToken(auth);
                responsavel = repositoryUser.findResponsavelByUsername(usernameAuth);
                responsavel.setAtivo(0);

                repositoryUser.save(responsavel);
                return UserResponse.builder()
                        .data(ResponsibleUserAdapter.toResponsibleUser(responsavel))
                        .date(LocalDate.now().toString()).build();
            case SELECT:
                usernameAuth = jwtTokenUtil.getUsernameFromToken(auth);
                responsavel = repositoryUser.findResponsavelByUsername(usernameAuth);

                return UserResponse.builder()
                        .data(ResponsibleUserAdapter.toResponsibleUser(responsavel))
                        .date(LocalDate.now().toString()).build();
            default:
                throw new InternalError("Função não implementada");

        }
    }

    @Override
    public UserTokenResponse execute(String auth, UserRequest userRequest) throws InvalidTokenException, UsernameInvalidException, UseCaseException {
        validatedToken(auth);

        try {
            String usernameAuth = jwtTokenUtil.getUsernameFromToken(auth);
            Responsavel responsavel = repositoryUser.findResponsavelByUsername(usernameAuth);

            if(!userRequest.getUsername().equals(responsavel.getUsername())){
                Responsavel checkUsername = repositoryUser.findResponsavelByUsername(userRequest.getUsername());
                if(!ObjectUtils.isEmpty(checkUsername)){
                    throw new UsernameInvalidException("Username já existe ou ocorreu um erro na persistência");
                }
            }

            Responsavel responsavelEditable = ResponsibleUserAdapter.createrResponsavel(userRequest, responsavel);
            repositoryUser.save(responsavelEditable);
            return UserTokenResponse.builder()
                    .data(ResponsibleUserAdapter.toResponsibleUser(responsavelEditable))
                    .jwtToken(jwtTokenUtil.generateToken(ResponsibleUserAdapter.toResponsibleUser(responsavelEditable)))
                    .date(LocalDate.now().toString()).build();
        }catch (UsernameInvalidException e){
            throw new UsernameInvalidException("Ocorreu um erro => "+ e.getMessage());
        }catch (Exception e){
            throw new UseCaseException("Ocorreu um erro na persistência",e);
        }

    }
}
