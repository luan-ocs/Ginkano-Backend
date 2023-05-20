package com.cavaleiros.ginkano.core.domain.response;

import com.cavaleiros.ginkano.core.domain.dto.Category;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CategoryResponse implements BodyResponse{

    Category category;
    
}
