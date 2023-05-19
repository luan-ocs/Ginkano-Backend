package com.cavaleiros.ginkano.core.domain.response.error;

import com.cavaleiros.ginkano.core.domain.response.BodyResponse;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ErrorResponse implements BodyResponse {

    ErrorDetail error;
    String date;
}
