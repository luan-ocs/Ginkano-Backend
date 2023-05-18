package com.cavaleiros.ginkano.endpoint;

import com.cavaleiros.ginkano.adapter.ErrorResponseAdapter;
import com.cavaleiros.ginkano.core.domain.request.RegisterUser;
import com.cavaleiros.ginkano.core.domain.response.*;
import com.cavaleiros.ginkano.usecase.RegisterUserUsecase;
import com.cavaleiros.ginkano.usecase.UserUsecase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final RegisterUserUsecase registerUser;
    private final UserUsecase userUsecase;

    @PostMapping("/register")
    public ResponseEntity<BodyResponse> registerUser(
            @RequestBody final RegisterUser user
    ){

        try{
            UserTokenResponse userTokenResponse = registerUser.execute(user);
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
