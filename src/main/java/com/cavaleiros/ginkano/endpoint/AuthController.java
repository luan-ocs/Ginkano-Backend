package com.cavaleiros.ginkano.endpoint;

import com.cavaleiros.ginkano.adapter.ErrorResponseAdapter;
import com.cavaleiros.ginkano.core.domain.response.Auth;
import com.cavaleiros.ginkano.core.domain.response.BodyResponse;
import com.cavaleiros.ginkano.core.domain.response.UserTokenResponse;
import com.cavaleiros.ginkano.exception.InvalidPasswordException;
import com.cavaleiros.ginkano.exception.UseCaseException;
import com.cavaleiros.ginkano.usecase.AuthUsecase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthUsecase authUsecase;

    @PostMapping(value = "/auth")
    public ResponseEntity<BodyResponse> getPersonaDomain(
            @RequestBody final Auth auth) {
        try {
            UserTokenResponse userTokenResponse = authUsecase.execute(auth.getUsername(), auth.getPassword());
            return ResponseEntity.ok(userTokenResponse);
        } catch (UseCaseException e){
            return ResponseEntity.internalServerError().body(ErrorResponseAdapter.toErrorResponse(e, "500"));
        } catch (InvalidPasswordException e){
            return ResponseEntity.badRequest().body(ErrorResponseAdapter.toErrorResponse(e, "400"));
        }
    }
}
