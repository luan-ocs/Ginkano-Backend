package com.cavaleiros.ginkano.usecase.impl;

import com.cavaleiros.ginkano.adapter.DonationAdapter;
import com.cavaleiros.ginkano.config.JwtTokenUtil;
import com.cavaleiros.ginkano.core.domain.request.DonationRequest;
import com.cavaleiros.ginkano.core.domain.response.DonationAllResponse;
import com.cavaleiros.ginkano.core.domain.response.DonationResponse;
import com.cavaleiros.ginkano.core.entity.Doacao;
import com.cavaleiros.ginkano.core.entity.Escola;
import com.cavaleiros.ginkano.exception.InvalidTokenException;
import com.cavaleiros.ginkano.repository.RepositoryDonation;
import com.cavaleiros.ginkano.repository.RepositorySchool;
import com.cavaleiros.ginkano.usecase.DonationUsecase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DonationUsecaseImpl implements DonationUsecase {
    private final JwtTokenUtil jwtTokenUtil;
    private final RepositoryDonation repositoryDonation;
    private final RepositorySchool repositorySchool;

    @Override
    public DonationResponse createDonation(DonationRequest donation, String auth) throws InvalidTokenException {
        validatedToken(auth);

        Escola escola = repositorySchool.findEscolaByToken(donation.getToken());

        Doacao doacao = DonationAdapter.toEntity(donation, escola);
        repositoryDonation.save(doacao);


        return DonationAdapter.fromDonationResponse(donation, escola);
    }

    @Override
    public DonationResponse updateDonation(DonationRequest donation, String auth) throws InvalidTokenException {
        validatedToken(auth);

        Escola escola = repositorySchool.findEscolaByToken(donation.getToken());

        Doacao doacao = repositoryDonation.findById(donation.getCode());
        doacao.setItem(donation.getItem());
        doacao.setPontos(donation.getPontos());
        doacao.setQuantidade(donation.getQuantidade());
        doacao.setEscola(escola);

        repositoryDonation.save(doacao);

        return DonationAdapter.fromDonationResponse(donation, escola);
    }

    @Override
    public DonationResponse deleteDonation(DonationRequest donation, String auth) {
        Escola escola = repositorySchool.findEscolaByToken(donation.getToken());

        Doacao doacao = repositoryDonation.findById(donation.getCode());
        repositoryDonation.delete(doacao);

        return DonationAdapter.fromDonationResponse(donation, escola);
    }

    @Override
    public DonationResponse selectDonation(String code, String auth) throws InvalidTokenException {
        validatedToken(auth);

        Doacao doacao = repositoryDonation.findById(Long.parseLong(code));

        return DonationAdapter.fromDonationRequest(doacao);
    }

    @Override
    public DonationAllResponse allDonations(String auth, String token) {
        Escola escola = repositorySchool.findEscolaByToken(token);
        List<Doacao> doacoes = repositoryDonation.findAllByEscola(escola);

        return DonationAdapter.fromDonationAllResponse(doacoes);
    }

    public void validatedToken(String auth) throws InvalidTokenException {
        Date expirationDate = jwtTokenUtil.getExpirationDateFromToken(auth);
        if(expirationDate.before(Date.from(Instant.now()))){
            throw new InvalidTokenException("Token expirado ou invalido");
        }
    }
}
