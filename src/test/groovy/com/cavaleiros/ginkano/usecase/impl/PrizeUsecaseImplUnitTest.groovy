package com.cavaleiros.ginkano.usecase.impl

import com.cavaleiros.ginkano.config.JwtTokenUtil
import com.cavaleiros.ginkano.core.domain.dto.Prize
import com.cavaleiros.ginkano.core.entity.Premio
import com.cavaleiros.ginkano.exception.InvalidTokenException
import com.cavaleiros.ginkano.repository.RepositoryPrize
import spock.lang.Specification
import spock.lang.Subject

import java.time.Instant

class PrizeUsecaseImplUnitTest extends Specification {

    private JwtTokenUtil jwtTokenUtil = Mock()
    private RepositoryPrize repositoryPrize = Mock()

    @Subject
    private PrizeUsecaseImpl prizeUsecase = new PrizeUsecaseImpl(jwtTokenUtil, repositoryPrize)

    def "Dado um auth válido, deve retornar todos os premios cadastrado"() {
        given: "Um JWT dentro da válidade"
        jwtTokenUtil.getExpirationDateFromToken(_ as String) >> Date.from(Instant.now().plusSeconds(100))

        and: "Uma consulta com um retorno válido"
        1 * repositoryPrize.findAll()
                >> { List.of(new Premio("premio", "descricao")) }

        when: "O método é executado"
        def response = prizeUsecase.execute("auth")

        then: "Deve retornar um response válido"
        verifyAll(response.data[0].prize as Prize) {
            it.nome == "premio"
            it.descricao == "descricao"
        }
    }

    def "Dado um auth válido, deve retornar o premio"() {
        given: "Um JWT dentro da válidade"
        jwtTokenUtil.getExpirationDateFromToken(_ as String) >> Date.from(Instant.now().plusSeconds(100))

        and: "Uma consulta com um retorno válido"
        1 * repositoryPrize.findPremioByNome(_ as String)
                >> { new Premio("premio", "descricao") }

        when: "O método é executado"
        def response = prizeUsecase.execute("auth", "1")

        then: "Deve retornar um response válido"
        verifyAll(response.prize as Prize) {
            it.nome == "premio"
            it.descricao == "descricao"
        }
    }

    def "Dado um auth expirado, deve retornar uma exception"() {
        given: "Um JWT dentro da válidade"
        jwtTokenUtil.getExpirationDateFromToken(_ as String) >> Date.from(Instant.now().minusMillis(100))

        when: "O método é executado"
        def response = prizeUsecase.execute("1", _ as String)

        then: "Deve retornar um response válido"
        thrown(InvalidTokenException)
    }

}
