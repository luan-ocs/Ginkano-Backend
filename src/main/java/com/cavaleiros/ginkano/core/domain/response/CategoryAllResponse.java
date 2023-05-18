package com.cavaleiros.ginkano.core.domain.response;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CategoryAllResponse {

    List<CategoryResponse> data;
    LocalDate localDate;
}
