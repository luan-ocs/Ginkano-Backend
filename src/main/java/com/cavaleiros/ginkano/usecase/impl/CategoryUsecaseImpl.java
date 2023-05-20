package com.cavaleiros.ginkano.usecase.impl;

import org.springframework.stereotype.Service;

import com.cavaleiros.ginkano.adapter.CategoryAdapter;
import com.cavaleiros.ginkano.config.JwtTokenUtil;
import com.cavaleiros.ginkano.core.domain.response.CategoryAllResponse;
import com.cavaleiros.ginkano.core.domain.response.CategoryResponse;
import com.cavaleiros.ginkano.core.entity.Categoria;
import com.cavaleiros.ginkano.exception.InvalidTokenException;
import com.cavaleiros.ginkano.repository.RepositoryCategory;
import com.cavaleiros.ginkano.usecase.CategoryUseCase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryUsecaseImpl implements CategoryUseCase{
    private final JwtTokenUtil jwtTokenUtil;
    private final RepositoryCategory repositoryCategory;

    @Override
    public CategoryAllResponse execute(String auth) throws InvalidTokenException{
        validatedToken(auth);

        List<Categoria> categorias = repositoryCategory.findAll();
        List<CategoryResponse> categories = new ArrayList<>();

        categorias.forEach(c -> categories.add(CategoryAdapter.toCategoryResponse(c)));

        return CategoryAllResponse.builder().data(categories).localDate(LocalDate.now()).build();
    }

    @Override
    public CategoryResponse execute(String token, String auth) throws InvalidTokenException{
        validatedToken(auth);

        Categoria categoria = repositoryCategory.findCategoriaById(Integer.parseInt(token));
        return CategoryResponse.builder().category(CategoryAdapter.toCategoria(categoria)).build();
    }
    
    public void validatedToken(String auth) throws InvalidTokenException {
        Date expirationDate = jwtTokenUtil.getExpirationDateFromToken(auth);
        if(expirationDate.before(Date.from(Instant.now()))){
            throw new InvalidTokenException("Token expirado ou invalido");
        }
    }
}
