package com.cavaleiros.ginkano.core.domain.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class DonationAllResponse implements BodyResponse{

    List<DonationResponse> donations;
}
