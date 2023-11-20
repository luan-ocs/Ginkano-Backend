package com.cavaleiros.ginkano.usecase.impl

import com.cavaleiros.ginkano.config.JwtTokenUtil
import com.cavaleiros.ginkano.core.domain.dto.PersonaDomain
import com.cavaleiros.ginkano.core.domain.response.UserTokenResponse
import com.cavaleiros.ginkano.core.entity.Responsavel
import com.cavaleiros.ginkano.exception.InvalidPasswordException
import com.cavaleiros.ginkano.exception.InvalidTokenException
import com.cavaleiros.ginkano.exception.UseCaseException
import com.cavaleiros.ginkano.repository.RepositoryUser
import spock.lang.Specification
import spock.lang.Subject

import java.time.Instant

class AuthUsecaseImplUnitTest extends Specification {

    private RepositoryUser repositoryUser = Mock();
    private JwtTokenUtil jwtTokenUtil = Mock()

    @Subject
    private AuthUsecaseImpl authUsecase = new AuthUsecaseImpl(repositoryUser, jwtTokenUtil)

    def "deve retornar um responsable válido"() {
        given: "Um responsable válido"
            repositoryUser.findResponsavelByUsername(_ as String) >> {
                return new Responsavel("arthurhac","Arthur","Cruz",1,"","1234",1)
            }
        when:"O método é executado"
            def response = authUsecase.execute("arthurhac","1234")

        then: "Deve retornar um response válido"
            verifyAll(response as UserTokenResponse) {
                verifyAll(response.data as PersonaDomain) {
                    it.firstname == "Arthur"
                    it.lastname ==  "Cruz"
                    it.username == "arthurhac"
                }
            }
    }

    def "Dado a senha incorreta, deve lançar uma exception InvalidPasswordException"() {
        given: "Um responsable válido"
        repositoryUser.findResponsavelByUsername("arthurhac") >> {
            return new Responsavel("arthurhac","Arthur","Cruz",1,"","incorreto",1)
        }
        when:"O método é executado"
        def response = authUsecase.execute("arthurhac","1234")

        then: "Deve retornar um response válido"
        thrown(InvalidPasswordException)
    }

    def "Dado um erro genérico, deve lançar uma exception UseCaseException"() {
        given: "Um responsable válido"
        repositoryUser.findResponsavelByUsername("arthurhac") >> {
            throw new Exception("ocorreu um erro")
        }
        when:"O método é executado"
        def response = authUsecase.execute("arthurhac","1234")

        then: "Deve retornar um response válido"
        thrown(UseCaseException)
    }

}
