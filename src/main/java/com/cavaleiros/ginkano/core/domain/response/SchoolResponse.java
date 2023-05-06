package com.cavaleiros.ginkano.core.domain.response;

import com.cavaleiros.ginkano.core.domain.dto.School;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SchoolResponse implements BodyResponse{

    School school;
}
