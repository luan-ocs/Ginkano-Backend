package com.cavaleiros.ginkano.endpoint;

import com.cavaleiros.ginkano.adapter.ErrorResponseAdapter;
import com.cavaleiros.ginkano.core.domain.response.BodyResponse;
import com.cavaleiros.ginkano.core.domain.response.CategoryAllResponse;
import com.cavaleiros.ginkano.usecase.CategoryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryUseCase categoryUseCase;

    @GetMapping("/all")
    public ResponseEntity<BodyResponse> getCategory(
            @RequestHeader final String authorization
    ){
        try{
            CategoryAllResponse category =  categoryUseCase.execute(authorization);
            return ResponseEntity.ok(category);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(ErrorResponseAdapter.toErrorResponse(e, "400"));
        }
    }
}
