package com.cavaleiros.ginkano.core.domain.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Auth {
    String username;
    String password;
}
