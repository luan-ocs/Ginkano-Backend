package com.cavaleiros.ginkano.usecase.impl;

import com.cavaleiros.ginkano.config.JwtTokenUtil;
import com.cavaleiros.ginkano.exception.InvalidTokenException;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
public class BaseUsecase {

    @Autowired
    protected final JwtTokenUtil jwtTokenUtil;

    public void validatedToken(String auth) throws InvalidTokenException {
        Date expirationDate = jwtTokenUtil.getExpirationDateFromToken(auth);
        if(expirationDate.before(Date.from(Instant.now()))){
            throw new InvalidTokenException("Token expirado ou invalido");
        }
    }

    public static String generateHexSet() {
        Set<String> set = new HashSet<>();
        Random random = new Random();

        while (set.size() < 5) {
            int randomNumber = random.nextInt(16);
            String hexDigit = Integer.toHexString(randomNumber).toUpperCase();
            set.add(hexDigit);
        }

        return String.join("", set);
    }
}
