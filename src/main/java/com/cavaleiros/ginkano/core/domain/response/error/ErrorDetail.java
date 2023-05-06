package com.cavaleiros.ginkano.core.domain.response.error;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDetail {

    String code;
    String title;
    String detail;
}
