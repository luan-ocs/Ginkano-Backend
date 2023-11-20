package com.cavaleiros.ginkano.usecase.impl

import com.cavaleiros.ginkano.config.JwtTokenUtil
import com.cavaleiros.ginkano.core.domain.constants.Functions
import com.cavaleiros.ginkano.core.domain.dto.PersonaDomain
import com.cavaleiros.ginkano.core.domain.request.UserRequest
import com.cavaleiros.ginkano.core.entity.Responsavel
import com.cavaleiros.ginkano.exception.UseCaseException
import com.cavaleiros.ginkano.repository.RepositoryUser
import spock.lang.Specification
import spock.lang.Subject

import java.time.Instant

class UserUsecaseImplUnitTest extends Specification {

    private RepositoryUser repositoryUser = Mock()
    private JwtTokenUtil jwtTokenUtil = Mock()

    @Subject
    private UserUsecaseImpl userUsecase = new UserUsecaseImpl(jwtTokenUtil, repositoryUser)

    def "Dado um auth válido, deve deletar um usuario"() {
        given: "Um JWT dentro da válidade"
        jwtTokenUtil.getExpirationDateFromToken(_ as String) >> Date.from(Instant.now().plusSeconds(100))

        and:"Um username valido retirado do gateway"
        1 * jwtTokenUtil.getUsernameFromToken("auth") >>  { "usernameAuth" }

        and: "Uma consulta com um retorno válido"
        1 * repositoryUser.findResponsavelByUsername("usernameAuth")
                >> { new Responsavel( "username",  'firstname',  "lastname",  1,  "ocupacao",  "password",  1) }

        when: "O método é executado"
        def response = userUsecase.execute("auth", Functions.DELETE)

        then: "Deve retornar um response válido"
        verifyAll(response.data as PersonaDomain) {
            it.username == "username"
            it.firstname == "firstname"
        }
    }

    def "Dado um auth válido, deve retornar um usuário válido"() {
        given: "Um JWT dentro da válidade"
        jwtTokenUtil.getExpirationDateFromToken(_ as String) >> Date.from(Instant.now().plusSeconds(100))

        and:"Um username valido retirado do gateway"
        1 * jwtTokenUtil.getUsernameFromToken("auth") >>  { "usernameAuth" }

        and: "Uma consulta com um retorno válido"
        1 * repositoryUser.findResponsavelByUsername("usernameAuth")
                >> { new Responsavel( "username",  'firstname',  "lastname",  1,  "ocupacao",  "password",  1) }

        when: "O método é executado"
        def response = userUsecase.execute("auth", Functions.SELECT)

        then: "Deve retornar um response válido"
        verifyAll(response.data as PersonaDomain) {
            it.username == "username"
            it.firstname == "firstname"
        }
    }

    def "Deve cadastrar o usuário e retornar um UserTokenResponse"() {
        given: "Um JWT dentro da válidade"
        jwtTokenUtil.getExpirationDateFromToken(_ as String) >> Date.from(Instant.now().plusSeconds(100))

        and:"Um username valido retirado do gateway"
        1 * jwtTokenUtil.getUsernameFromToken("auth") >>  { "usernameAuth" }

        and: "Uma consulta com um retorno válido"
        1 * repositoryUser.findResponsavelByUsername("usernameAuth")
                >> {  }

        and:"Dado um User não existente na base de dados"
        UserRequest userRequest =
                UserRequest.builder()
                        .username("novo")
                        .firstname("novo_fisrt")
                        .lastname("novo_last")
                        .ocupacao("novo_ocupacao")
                        .conditions(1).build()

        when: "O método é executado"
        def response = userUsecase.execute("auth",userRequest)

        then: "Deve retornar um response válido"
        thrown(UseCaseException)
    }
}
