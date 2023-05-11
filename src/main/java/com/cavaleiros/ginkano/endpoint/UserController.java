package com.cavaleiros.ginkano.endpoint;

import com.cavaleiros.ginkano.adapter.ErrorResponseAdapter;
import com.cavaleiros.ginkano.core.domain.request.RegisterUser;
import com.cavaleiros.ginkano.core.domain.response.*;
import com.cavaleiros.ginkano.usecase.RegisterUserUsecase;
import com.cavaleiros.ginkano.usecase.SchoolUsecase;
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
    private final SchoolUsecase schoolUsecase;

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

    @GetMapping("/school/all")
    public ResponseEntity<BodyResponse> getEscolas(
            @RequestHeader final String authorization
    ){
        try{
            SchoolAllResponse schoolAllResponse = schoolUsecase.execute(authorization);
            return ResponseEntity.ok(schoolAllResponse);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(ErrorResponseAdapter.toErrorResponse(e, "400"));
        }
    }

    @GetMapping("/school/")
    public ResponseEntity<BodyResponse> getEscolas(
            @RequestHeader final String token,
            @RequestHeader final String authorization
    ){
        try{
            SchoolResponse schoolResponse = schoolUsecase.execute(token, authorization);
            return ResponseEntity.ok(schoolResponse);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(ErrorResponseAdapter.toErrorResponse(e, "400"));
        }
    }
}
