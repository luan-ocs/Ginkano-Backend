package com.cavaleiros.ginkano.usecase.impl

import com.cavaleiros.ginkano.config.JwtTokenUtil
import com.cavaleiros.ginkano.core.domain.constants.Functions
import com.cavaleiros.ginkano.core.domain.dto.Group
import com.cavaleiros.ginkano.core.domain.request.GroupRequest
import com.cavaleiros.ginkano.core.entity.Escola
import com.cavaleiros.ginkano.repository.RepositorySchool
import spock.lang.Specification
import spock.lang.Subject

import java.time.Instant

class GroupUsecaseImplUnitTest extends Specification {

    private RepositorySchool repositorySchool = Mock()
    private JwtTokenUtil jwtTokenUtil = Mock()

    @Subject
    private GroupUsecaseImpl groupUsecase = new GroupUsecaseImpl(jwtTokenUtil, repositorySchool)

    def "Dado um auth válido, deve retornar todos os grupos cadastrado"() {
        given: "Um JWT dentro da válidade"
        jwtTokenUtil.getExpirationDateFromToken(_ as String) >> Date.from(Instant.now().plusSeconds(100))

        and: "Uma escola válida"
        1 * repositorySchool.findAllByAtivo(1)
                >> { List.of(new Escola(1l, "Escola", "1", "Endereco", 1)) }

        when: "O método é executado"
        def response = groupUsecase.execute("auth")

        then: "Deve retornar um response válido"
        verifyAll(response.data[0].group as Group) {
            it.nome == "Escola"
            it.endereco == "Endereco"
        }
    }

    def "Dado um auth válido, Deve retornar um grupo"() {
        given: "Um JWT dentro da válidade"
        jwtTokenUtil.getExpirationDateFromToken(_ as String) >> Date.from(Instant.now().plusSeconds(100))

        and: "Uma escola válida"
        1 * repositorySchool.findEscolaByToken("1")
                >> { new Escola(1l, "Escola", "1", "Endereco", 1) }

        when: "O método é executado"
        def response = groupUsecase.execute("1", "auth", Functions.SELECT)

        then: "Deve retornar um response válido"
        verifyAll(response.group as Group) {
            it.nome == "Escola"
            it.endereco == "Endereco"
        }
    }

    def "Dado um auth válido, Deve deletar um grupo"() {
        given: "Um JWT dentro da válidade"
        jwtTokenUtil.getExpirationDateFromToken(_ as String) >> Date.from(Instant.now().plusSeconds(100))

        and: "Uma escola válida"
        1 * repositorySchool.findEscolaByToken("1")
                >> { new Escola(1l, "Escola", "1", "Endereco", 1) }

        when: "O método é executado"
        def response = groupUsecase.execute("1", "auth", Functions.DELETE)

        then: "Deve retornar um response válido"
        verifyAll(response.group as Group) {
            it.nome == "Escola"
            it.endereco == "Endereco"
        }
    }

    def "Dado um auth válido, Deve alterar um grupo"() {
        given: "Um JWT dentro da válidade"
        jwtTokenUtil.getExpirationDateFromToken(_ as String) >> Date.from(Instant.now().plusSeconds(100))

        and: "Uma escola válida"
        1 * repositorySchool.findEscolaByToken("1")
                >> { new Escola(1l, "Escola", "1", "Endereco", 1) }

        and:"GroupRequest com as informações para alterar"
        GroupRequest groupRequest = GroupRequest.builder()
                .nome("alterado")
                .endereco("alterado")
                .build()

        when: "O método é executado"
        def response = groupUsecase.execute(groupRequest, "auth", "1")

        then: "Deve retornar um response válido"
        verifyAll(response.group as Group) {
            it.nome == "alterado"
            it.endereco == "alterado"
        }
    }

    def "Dado um auth válido, Deve retornar um grupo novo"() {
        given: "Um JWT dentro da válidade"
        jwtTokenUtil.getExpirationDateFromToken(_ as String) >> Date.from(Instant.now().plusSeconds(100))

        and: "Uma escola válida"
        repositorySchool.findEscolaByToken("1")
                >> { new Escola(1l, "Escola", "1", "Endereco", 1) }

        and:"GroupRequest com as informações para alterar"
        GroupRequest groupRequest = GroupRequest.builder()
                .nome("novo")
                .endereco("novo")
                .build()

        when: "O método é executado"
        def response = groupUsecase.execute(groupRequest, "auth")

        then: "Deve retornar um response válido"
        verifyAll(response.group as Group) {
            it.nome == "novo"
            it.endereco == "novo"
        }
    }

    def "Dado um auth válido, Deve retornar um grupo especifico"() {
        given: "Uma escola válida"
        repositorySchool.findEscolaByToken("1")
                >> { new Escola(1l, "Escola", "1", "Endereco", 1) }

        when: "O método é executado"
        def response = groupUsecase.executeId("1")

        then: "Deve retornar um response válido"
        verifyAll(response.group as Group) {
            it.nome == "Escola"
            it.endereco == "Endereco"
        }
    }
}
