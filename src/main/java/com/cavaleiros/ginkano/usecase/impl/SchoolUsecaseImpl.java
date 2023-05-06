package com.cavaleiros.ginkano.usecase.impl;

import com.cavaleiros.ginkano.adapter.SchoolAdapter;
import com.cavaleiros.ginkano.config.JwtTokenUtil;
import com.cavaleiros.ginkano.core.domain.dto.School;
import com.cavaleiros.ginkano.core.domain.response.SchoolAllResponse;
import com.cavaleiros.ginkano.core.domain.response.SchoolResponse;
import com.cavaleiros.ginkano.core.entity.Escola;
import com.cavaleiros.ginkano.exception.InvalidTokenException;
import com.cavaleiros.ginkano.repository.RepositorySchool;
import com.cavaleiros.ginkano.usecase.SchoolUsecase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SchoolUsecaseImpl implements SchoolUsecase {

    private final JwtTokenUtil jwtTokenUtil;
    private final RepositorySchool repositorySchool;

    @Override
    public SchoolAllResponse execute(String auth) throws InvalidTokenException {
        validatedToken(auth);

        List<Escola> escolas = repositorySchool.findAll();
        List<SchoolResponse> schools = new ArrayList<>();

        escolas.forEach(s -> schools.add(SchoolAdapter.toSchoolResponse(s)));

        return SchoolAllResponse.builder()
                .data(schools)
                .localDate(LocalDate.now())
                .build();
    }

    @Override
    public SchoolResponse execute(String token, String auth) throws InvalidTokenException {
        validatedToken(auth);

        Escola escola = repositorySchool.findEscolaByToken(token);
        return SchoolResponse.builder().school(SchoolAdapter.toSchool(escola)).build();
    }

    public void validatedToken(String auth) throws InvalidTokenException {
        Date expirationDate = jwtTokenUtil.getExpirationDateFromToken(auth);
        if(expirationDate.before(Date.from(Instant.now()))){
            throw new InvalidTokenException("Token expirado ou invalido");
        }
    }
}
