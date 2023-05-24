package com.cavaleiros.ginkano.endpoint;

import com.cavaleiros.ginkano.adapter.ErrorResponseAdapter;
import com.cavaleiros.ginkano.core.domain.constants.Functions;
import com.cavaleiros.ginkano.core.domain.request.RegisterUserRequest;
import com.cavaleiros.ginkano.core.domain.request.UserRequest;
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
            @RequestBody final RegisterUserRequest user
    ){

        try{
            UserTokenResponse userTokenResponse = registerUser.execute(user);
            return ResponseEntity.ok().body(userTokenResponse);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(ErrorResponseAdapter.toErrorResponse(e, "400"));
        }
    }

    @GetMapping("")
    public ResponseEntity<BodyResponse> getUser(
            @RequestHeader final String authorization
    ){
        try{
            UserResponse userResponse = userUsecase.execute(authorization, Functions.SELECT);
            return ResponseEntity.ok(userResponse);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(ErrorResponseAdapter.toErrorResponse(e, "400"));
        }
    }

    @PostMapping("/edit")
    public ResponseEntity<BodyResponse> editUser(
            @RequestBody final UserRequest user,
            @RequestHeader final String authorization
    ){
        try{
            UserTokenResponse userResponse = userUsecase.execute(authorization, user);
            return ResponseEntity.ok(userResponse);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(ErrorResponseAdapter.toErrorResponse(e, "400"));
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<BodyResponse> deleteUser(
            @RequestHeader final String authorization
    ){
        try{
            UserResponse userResponse = userUsecase.execute(authorization, Functions.DELETE);
            return ResponseEntity.ok(userResponse);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(ErrorResponseAdapter.toErrorResponse(e, "400"));
        }
    }
}
