package com.cavaleiros.ginkano.adapter;

import com.cavaleiros.ginkano.core.domain.response.error.ErrorDetail;
import com.cavaleiros.ginkano.core.domain.response.error.ErrorResponse;

import java.time.LocalDate;

public class ErrorResponseAdapter {

    public static ErrorResponse toErrorResponse(Exception e, String code){
        return ErrorResponse.builder()
                .date(LocalDate.now())
                .error(createErrorDetail(e, code))
                .build();
    }

    private static ErrorDetail createErrorDetail(Exception e, String code){
        return ErrorDetail.builder()
                .detail(e.getMessage())
                .title(e.getLocalizedMessage())
                .code(code).build();
    }
}
