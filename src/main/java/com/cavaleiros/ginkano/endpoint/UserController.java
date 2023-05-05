package com.cavaleiros.ginkano.endpoint;

import com.cavaleiros.ginkano.adapter.ErrorResponseAdapter;
import com.cavaleiros.ginkano.core.domain.response.BodyResponse;
import com.cavaleiros.ginkano.core.domain.response.UserResponse;
import com.cavaleiros.ginkano.core.domain.response.UserTokenResponse;
import com.cavaleiros.ginkano.core.domain.response.error.ErrorResponse;
import com.cavaleiros.ginkano.usecase.RegisterUserUsecase;
import com.cavaleiros.ginkano.usecase.UserUsecase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final RegisterUserUsecase registerUser;
    private final UserUsecase userUsecase;

    @PostMapping("/register")
    public ResponseEntity<BodyResponse> registerUser(
            @RequestHeader final String username,
            @RequestHeader final String password,
            @RequestHeader final String firstname,
            @RequestHeader final String lastname,
            @RequestHeader final String ocupacao,
            @RequestHeader final Integer conditions
    ){

        try{
            UserTokenResponse userTokenResponse = registerUser.execute(username, firstname, lastname, conditions, ocupacao, password);
            return ResponseEntity.ok().body(userTokenResponse);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(ErrorResponseAdapter.toErrorResponse(e, "400"));
        }
    }

    @GetMapping("/")
    public ResponseEntity<BodyResponse> getUser(
            @RequestHeader final String authorization
    ){
        try{
            UserResponse userResponse = userUsecase.execute(authorization);
            return ResponseEntity.ok(userResponse);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(ErrorResponseAdapter.toErrorResponse(e, "400"));
        }


    }
}
