package com.cavaleiros.ginkano.adapter;

import com.cavaleiros.ginkano.core.domain.request.DonationRequest;
import com.cavaleiros.ginkano.core.domain.response.DonationAllResponse;
import com.cavaleiros.ginkano.core.domain.response.DonationResponse;
import com.cavaleiros.ginkano.core.entity.Doacao;
import com.cavaleiros.ginkano.core.entity.Escola;

import java.util.List;
import java.util.stream.Collectors;

public class DonationAdapter {

    public static Doacao toEntity(DonationRequest request, Escola escola) {
        return Doacao.builder()
                .id(request.getCode())
                .item(request.getItem())
                .doador(request.getDoador())
                .quantidade(request.getQuantidade())
                .pontos(request.getPontos())
                .escola(escola)
                .build();
    }

    public static DonationResponse fromDonationResponse(DonationRequest request, Escola escola) {
        return DonationResponse.builder()
                .code(request.getCode())
                .item(request.getItem())
                .doador(request.getDoador())
                .quantidade(request.getQuantidade())
                .pontos(request.getPontos())
                .token(escola.getToken())
                .build();
    }

    public static DonationResponse fromDonationRequest(Doacao doacao) {
        return DonationResponse.builder()
                .code(doacao.getId())
                .item(doacao.getItem())
                .doador(doacao.getDoador())
                .quantidade(doacao.getQuantidade())
                .pontos(doacao.getPontos())
                .token(doacao.getEscola().getToken())
                .build();
    }

    public static DonationAllResponse fromDonationAllResponse(List<Doacao> doacaoList){
        List<DonationResponse> donationResponses = doacaoList.stream().map(e ->
                DonationResponse.builder()
                                .code(e.getId())
                                .item(e.getItem())
                                .doador(e.getDoador())
                                .quantidade(e.getQuantidade())
                                .pontos(e.getPontos())
                                .token(e.getEscola().getToken())
                                .build()).collect(Collectors.toList());

        return DonationAllResponse.builder()
                .donations(donationResponses)
                .build();
    }
}
