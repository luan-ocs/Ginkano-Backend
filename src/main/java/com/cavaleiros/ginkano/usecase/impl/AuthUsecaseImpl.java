package com.cavaleiros.ginkano.usecase.impl;

import com.cavaleiros.ginkano.adapter.ResponsibleUserAdapter;
import com.cavaleiros.ginkano.config.JwtTokenUtil;
import com.cavaleiros.ginkano.core.domain.dto.ResponsibleUser;
import com.cavaleiros.ginkano.core.entity.Responsavel;
import com.cavaleiros.ginkano.core.domain.response.UserTokenResponse;
import com.cavaleiros.ginkano.exception.InvalidPasswordException;
import com.cavaleiros.ginkano.exception.UseCaseException;
import com.cavaleiros.ginkano.repository.RepositoryUser;
import com.cavaleiros.ginkano.usecase.AuthUsecase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthUsecaseImpl implements AuthUsecase {

    private final RepositoryUser repositoryUser;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public UserTokenResponse execute(String username, String password) throws UseCaseException, InvalidPasswordException {

        try {
            Responsavel responsavel = repositoryUser.findResponsavelByUsername(username);
            validatedPassword(password, responsavel);

            ResponsibleUser responsibleUser = ResponsibleUserAdapter.toResponsibleUser(responsavel);
            String token = jwtTokenUtil.generateToken(responsibleUser);

            log.info("Entidade retornada -> ".concat(responsibleUser.toString()));
            return UserTokenResponse.builder()
                    .data(responsibleUser)
                    .date(LocalDateTime.now().toString())
                    .jwtToken(token)
                    .build();

        }catch (InvalidPasswordException e){
            log.info("[AuthUsecase] - Senha Incorreta");
           throw new InvalidPasswordException("Senha inválida");
        }catch (Exception e) {
            log.info("[AuthUsecase] - Erro no usecase", e);
            throw new UseCaseException("Ocorreu um erro no usecase de Auth ou o username não existe", e);
        }
    }

    private void validatedPassword(String password, Responsavel responsavel) throws InvalidPasswordException {
        if(!password.equals(responsavel.getPassword())){
           throw new InvalidPasswordException("Senha incorreta");
        }
    }

}
