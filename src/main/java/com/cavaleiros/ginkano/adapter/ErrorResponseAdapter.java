package com.cavaleiros.ginkano.adapter;

import com.cavaleiros.ginkano.core.domain.response.error.ErrorDetail;
import com.cavaleiros.ginkano.core.domain.response.error.ErrorResponse;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class ErrorResponseAdapter {

    public static ErrorResponse toErrorResponse(Exception e, String code){
        return ErrorResponse.builder()
                .date(LocalDate.now().toString())
                .error(createErrorDetail(e, code))
                .build();
    }

    private static ErrorDetail createErrorDetail(Exception e, String code){
        return ErrorDetail.builder()
                .detail(e.getMessage())
                .title("Ocorreu um erro")
                .code(code).build();
    }
}
