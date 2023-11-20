package com.cavaleiros.ginkano.usecase.impl

import com.cavaleiros.ginkano.config.JwtTokenUtil
import com.cavaleiros.ginkano.core.domain.dto.PersonaDomain
import com.cavaleiros.ginkano.core.domain.request.RegisterUserRequest
import com.cavaleiros.ginkano.core.entity.Responsavel
import com.cavaleiros.ginkano.exception.UsernameInvalidException
import com.cavaleiros.ginkano.repository.RepositoryUser
import spock.lang.Specification
import spock.lang.Subject

class RegisterUserImplUnitTest extends Specification {

    private RepositoryUser repositoryUser = Mock()
    private JwtTokenUtil jwtTokenUtil = Mock()

    @Subject
    RegisterUserImpl registerUser = new RegisterUserImpl(repositoryUser, jwtTokenUtil)

    def "Deve cadastrar o usuário e retornar um UserTokenResponse"() {
        given:"Dado um responsável não existente na base de dados"
        RegisterUserRequest registerUserRequest =
                RegisterUserRequest.builder().username("novo")
                                             .firstname("novo_fisrt")
                                             .lastname("novo_last")
                                             .ocupacao("novo_ocupacao")
                                             .conditions(1)
                                             .password("novo_pass").build()

        when: "O método é executado"
        def response = registerUser.execute(registerUserRequest)

        then: "Deve retornar um response válido"
        verifyAll(response.data as PersonaDomain) {
            it.username == "novo"
            it.firstname =="novo_fisrt"
            it.lastname == "novo_last"
        }
    }

    def "Deve lançar uma exception para um username já existente"() {
        given:"Dado um responsável que existente na base de dados"
        RegisterUserRequest registerUserRequest =
                RegisterUserRequest.builder().username("existe")
                        .firstname("novo_fisrt")
                        .lastname("novo_last")
                        .ocupacao("novo_ocupacao")
                        .conditions(1)
                        .password("novo_pass").build()

        and:"E um retorno válido do repository"
        repositoryUser.findResponsavelByUsername("existe") >> {
            new Responsavel("existe", "novo_fisrt", "novo_last", 1, "novo_ocupacao", "novo_pass", 1)
        }

        when: "O método é executado"
        def response = registerUser.execute(registerUserRequest)

        then: "Deve retornar um response válido"
        thrown(UsernameInvalidException)
    }

}
