package com.cavaleiros.ginkano.core.domain.response;

import com.cavaleiros.ginkano.core.domain.dto.PersonaDomain;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserResponse implements BodyResponse{

    PersonaDomain data;
    String date;
}
