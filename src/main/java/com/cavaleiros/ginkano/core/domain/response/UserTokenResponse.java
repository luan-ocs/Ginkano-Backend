package com.cavaleiros.ginkano.core.domain.response;

import com.cavaleiros.ginkano.core.domain.dto.PersonaDomain;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserTokenResponse implements BodyResponse{

    PersonaDomain data;
    String date;
    String jwtToken;
}
