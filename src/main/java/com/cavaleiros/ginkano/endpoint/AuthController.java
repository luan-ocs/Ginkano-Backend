package com.cavaleiros.ginkano.endpoint;

import com.cavaleiros.ginkano.adapter.ErrorResponseAdapter;
import com.cavaleiros.ginkano.core.domain.response.BodyResponse;
import com.cavaleiros.ginkano.core.domain.response.UserTokenResponse;
import com.cavaleiros.ginkano.exception.InvalidPasswordException;
import com.cavaleiros.ginkano.exception.UseCaseException;
import com.cavaleiros.ginkano.usecase.AuthUsecase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    @Autowired
    private AuthUsecase authUsecase;

    @GetMapping(value = "/auth")
    public ResponseEntity<BodyResponse> getPersonaDomain(
            @RequestHeader(value = "username") final String username,
            @RequestHeader(value = "password") final String password){

        try {
            UserTokenResponse userTokenResponse = authUsecase.execute(username, password);
            return ResponseEntity.ok(userTokenResponse);
        } catch (UseCaseException e){
            return ResponseEntity.internalServerError().body(ErrorResponseAdapter.toErrorResponse(e, "500"));
        } catch (InvalidPasswordException e){
            return ResponseEntity.badRequest().body(ErrorResponseAdapter.toErrorResponse(e, "404"));
        }
    }
}
