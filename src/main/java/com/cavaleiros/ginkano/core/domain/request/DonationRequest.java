package com.cavaleiros.ginkano.core.domain.request;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DonationRequest{

    private Long code;
    private String item;
    private String doador;
    private Double quantidade;
    private Long pontos;
    private String token;
}
