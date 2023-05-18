package com.cavaleiros.ginkano.usecase.impl;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cavaleiros.ginkano.adapter.PrizeAdapter;
import com.cavaleiros.ginkano.config.JwtTokenUtil;
import com.cavaleiros.ginkano.core.domain.response.PrizeAllResponse;
import com.cavaleiros.ginkano.core.domain.response.PrizeResponse;
import com.cavaleiros.ginkano.core.entity.Premio;
import com.cavaleiros.ginkano.exception.InvalidTokenException;
import com.cavaleiros.ginkano.repository.RepositoryPrize;
import com.cavaleiros.ginkano.usecase.PrizeUseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrizeUsecaseImpl implements PrizeUseCase{
    private final JwtTokenUtil jwtTokenUtil;
    private final RepositoryPrize repositoryPrize;

    @Override
    public PrizeAllResponse execute(String auth) throws InvalidTokenException{
        validatedToken(auth);

        List<Premio> premios = repositoryPrize.findAll();
        List<PrizeResponse> prizes = new ArrayList<>();

        premios.forEach(p -> prizes.add(PrizeAdapter.toPrizeResponse(p)));

        return PrizeAllResponse.builder().data(prizes).localDate(LocalDate.now()).build();
    }

    @Override
    public PrizeResponse execute(String token, String auth)throws InvalidTokenException{
        validatedToken(auth);

        Premio premio = repositoryPrize.findPremioByNome(auth);
        return PrizeResponse.builder().prize(PrizeAdapter.toPrize(premio)).build();
    }

    public void validatedToken(String auth) throws InvalidTokenException {
        Date expirationDate = jwtTokenUtil.getExpirationDateFromToken(auth);
        if(expirationDate.before(Date.from(Instant.now()))){
            throw new InvalidTokenException("Token expirado ou invalido");
        }
    }
}
