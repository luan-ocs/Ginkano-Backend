package com.cavaleiros.ginkano.core.domain.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
public class SchoolAllResponse implements BodyResponse{

    List<SchoolResponse> data;
    LocalDate localDate;
}
