package com.cavaleiros.ginkano.usecase.impl;

import com.cavaleiros.ginkano.adapter.GroupAdapter;
import com.cavaleiros.ginkano.config.JwtTokenUtil;
import com.cavaleiros.ginkano.core.domain.constants.Functions;
import com.cavaleiros.ginkano.core.domain.request.GroupRequest;
import com.cavaleiros.ginkano.core.domain.response.GroupAllResponse;
import com.cavaleiros.ginkano.core.domain.response.GroupResponse;
import com.cavaleiros.ginkano.core.entity.Escola;
import com.cavaleiros.ginkano.exception.InvalidTokenException;
import com.cavaleiros.ginkano.repository.RepositorySchool;
import com.cavaleiros.ginkano.usecase.GroupUsecase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class GroupUsecaseImpl implements GroupUsecase {

    private final JwtTokenUtil jwtTokenUtil;
    private final RepositorySchool repositorySchool;

    @Override
    public GroupAllResponse execute(String auth) throws InvalidTokenException {
        validatedToken(auth);

        List<Escola> escolas = repositorySchool.findAll();
        List<GroupResponse> schools = new ArrayList<>();

        escolas.forEach(s -> schools.add(GroupAdapter.toSchoolResponse(s)));

        return GroupAllResponse.builder()
                .data(schools)
                .datetime(LocalDate.now().toString())
                .build();
    }

    @Override
    public GroupResponse execute(String token, String auth, Functions function) throws InvalidTokenException {
        validatedToken(auth);

        Escola escola;

        switch (function){
            case SELECT:
                escola = repositorySchool.findEscolaByToken(token);
                return GroupResponse.builder().group(GroupAdapter.toSchool(escola)).build();
            case DELETE:
                escola = repositorySchool.findEscolaByToken(token);
                escola.setAtivo(0);
                repositorySchool.save(escola);
                return GroupResponse.builder().group(GroupAdapter.toSchool(escola)).build();
            default:
                throw new InternalError("Função não implementada");
        }
    }

    @Override
    public GroupResponse execute(GroupRequest groupRequest, String auth, String token) throws InvalidTokenException {
        validatedToken(auth);
        Escola escola;

        escola = repositorySchool.findEscolaByToken(token);
        escola.setEndereco(groupRequest.getEndereco());
        escola.setNome(groupRequest.getNome());

        repositorySchool.save(escola);
        return GroupResponse.builder().group(GroupAdapter.toSchool(escola)).build();
    }

    @Override
    public GroupResponse execute(GroupRequest groupRequest, String auth) throws InvalidTokenException {
        validatedToken(auth);

        Escola escola = Escola.builder()
                .nome(groupRequest.getNome())
                .endereco(groupRequest.getEndereco())
                .token(generateHexSet())
                .ativo(1)
                .build();

        repositorySchool.save(escola);
        return GroupResponse.builder().group(GroupAdapter.toSchool(escola)).build();
    }

    public void validatedToken(String auth) throws InvalidTokenException {
        Date expirationDate = jwtTokenUtil.getExpirationDateFromToken(auth);
        if(expirationDate.before(Date.from(Instant.now()))){
            throw new InvalidTokenException("Token expirado ou invalido");
        }
    }

    public static String generateHexSet() {
        Set<String> set = new HashSet<>();
        Random random = new Random();

        while (set.size() < 5) {
            int randomNumber = random.nextInt(16);
            String hexDigit = Integer.toHexString(randomNumber).toUpperCase();
            set.add(hexDigit);
        }

        return String.join("", set);
    }
}
