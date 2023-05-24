package com.cavaleiros.ginkano.adapter;

import com.cavaleiros.ginkano.core.domain.dto.Category;
import com.cavaleiros.ginkano.core.domain.dto.Type;
import com.cavaleiros.ginkano.core.domain.response.CategoryResponse;
import com.cavaleiros.ginkano.core.entity.Categoria;
import com.cavaleiros.ginkano.core.entity.Tipo;

public class CategoryAdapter {
    public static Category toCategoria(Categoria categoria){
        return Category.builder()
                    .descricao(categoria.getDescricao())
                    .type(toType(categoria.getTipo()))
                    .build();
    }

    public static CategoryResponse toCategoryResponse(Categoria categoria){
        return CategoryResponse.builder()
                    .category(toCategoria(categoria))
                    .build();
    }

    private static Type toType(Tipo tipo){
        return Type.builder()
                .descricao(tipo.getDescricao())
                .build();
    }

}
