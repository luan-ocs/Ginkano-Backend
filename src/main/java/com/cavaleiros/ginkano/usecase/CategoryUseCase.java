package com.cavaleiros.ginkano.usecase;

import com.cavaleiros.ginkano.core.domain.response.CategoryAllResponse;
import com.cavaleiros.ginkano.core.domain.response.CategoryResponse;
import com.cavaleiros.ginkano.exception.InvalidTokenException;

public interface CategoryUseCase {
    
    CategoryAllResponse execute(String auth) throws InvalidTokenException;

    CategoryResponse execute(String token, String auth) throws InvalidTokenException;
}
