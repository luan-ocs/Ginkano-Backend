package com.cavaleiros.ginkano.core.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class DonationResponse implements BodyResponse{

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Long code;
    private String item;
    private String doador;
    private Double quantidade;
    private Long pontos;
    private String token;
}
