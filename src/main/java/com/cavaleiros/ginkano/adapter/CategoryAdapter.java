package com.cavaleiros.ginkano.adapter;

import com.cavaleiros.ginkano.core.domain.dto.Category;
import com.cavaleiros.ginkano.core.domain.response.CategoryResponse;
import com.cavaleiros.ginkano.core.entity.Categoria;

public class CategoryAdapter {
    public static Category toCategoria(Categoria categoria){
        return Category.builder()
                    .descricao(categoria.getDescricao())
                    .tipo(categoria.getTipo())
                    .build();
    }

    public static CategoryResponse toCategoryResponse(Categoria categoria){
        return CategoryResponse.builder()
                    .category(toCategoria(categoria))
                    .build();
    }
}
