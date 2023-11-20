package com.cavaleiros.ginkano.usecase.impl

import com.cavaleiros.ginkano.config.JwtTokenUtil
import com.cavaleiros.ginkano.core.domain.request.DonationRequest
import com.cavaleiros.ginkano.core.domain.response.DonationAllResponse
import com.cavaleiros.ginkano.core.domain.response.DonationResponse
import com.cavaleiros.ginkano.core.entity.Doacao
import com.cavaleiros.ginkano.core.entity.Escola
import com.cavaleiros.ginkano.exception.InvalidTokenException
import com.cavaleiros.ginkano.repository.RepositoryDonation
import com.cavaleiros.ginkano.repository.RepositorySchool
import spock.lang.Specification
import spock.lang.Subject

import java.time.Instant

class DonationUsecaseImplUnitTest extends Specification {

    private JwtTokenUtil jwtTokenUtil = Mock()
    private RepositoryDonation repositoryDonation = Mock()
    private RepositorySchool repositorySchool = Mock()

    @Subject
    private DonationUsecaseImpl donationUsecase = new DonationUsecaseImpl(jwtTokenUtil, repositoryDonation, repositorySchool)

    def "Dado um auth válido, deve retornar um response valido após a criação do Donation"() {
        given: "Um JWT dentro da válidade"
        jwtTokenUtil.getExpirationDateFromToken(_ as String) >> Date.from(Instant.now().plusSeconds(100))

        and: "Um donation válido"
        def donationRequest =
                DonationRequest.builder()
                        .token("1")
                        .doador("doador")
                        .item("item")
                        .quantidade(1d)
                        .pontos(1l)
                        .code(1)
                        .build()

        and: "Uma escola válida"
        repositorySchool.findEscolaByToken(_ as String)
                >> { new Escola(1l, "Escola", "1", "Endereco", 1) }

        when: "O método é executado"
        def response = donationUsecase.createDonation(donationRequest, "1")

        then: "Deve retornar um response válido"
        verifyAll(response as DonationResponse) {
            it.item == "item"
            it.quantidade == 1
            it.doador == "doador"
        }
    }

    def "Dado um auth válido, deve retornar um response valido após a atualização do Donation"() {
        given: "Um JWT dentro da válidade"
        jwtTokenUtil.getExpirationDateFromToken(_ as String) >> Date.from(Instant.now().plusSeconds(100))

        and: "Um donation válido"
        def donationRequest =
                DonationRequest.builder()
                        .token("1")
                        .doador("doador")
                        .item("item")
                        .quantidade(1d)
                        .pontos(1l)
                        .code(1)
                        .build()

        and: "Uma escola válida"
        Escola escola = new Escola(1l, "Escola", "1", "Endereco", 1)
        repositorySchool.findEscolaByToken(_ as String)
                >> { escola }

        and: "Uma doação válida"
        repositoryDonation.findById(donationRequest.code)
                >> { Doacao.builder().item("item").pontos(1).quantidade(1).escola(escola).build() }

        when: "O método é executado"
        def response = donationUsecase.updateDonation(donationRequest, "1")

        then: "Deve retornar um response válido"
        verifyAll(response as DonationResponse) {
            it.item == "item"
            it.quantidade == 1
            it.doador == "doador"
        }


    }

    def "Dado um auth válido, deve retornar um response valido após deletar o Donation"() {
        given: "Um JWT dentro da válidade"
        jwtTokenUtil.getExpirationDateFromToken(_ as String) >> Date.from(Instant.now().plusSeconds(100))

        and: "Um donation válido"
        def donationRequest =
                DonationRequest.builder()
                        .token("1")
                        .doador("doador")
                        .item("item")
                        .quantidade(1d)
                        .pontos(1l)
                        .code(1)
                        .build()

        and: "Uma escola válida"
        Escola escola = new Escola(1l, "Escola", "1", "Endereco", 1)
        repositorySchool.findEscolaByToken(_ as String)
                >> { escola }

        and: "Uma doação válida"
        Doacao doacao = Doacao.builder().item("item").pontos(1).quantidade(1).escola(escola).build()
        repositoryDonation.findById(donationRequest.code)
                >> { doacao }

        and:"O Donation é deletado"
        1 * repositoryDonation.delete(doacao)

        when: "O método é executado"
        def response = donationUsecase.deleteDonation(donationRequest, "1")

        then: "Deve retornar um response válido"
        verifyAll(response as DonationResponse) {
            it.item == "item"
            it.quantidade == 1
            it.doador == "doador"
        }
    }

    def "Dado um auth válido, deve retornar um response valido após selecionar o Donation"() {
        given: "Um JWT dentro da válidade"
        jwtTokenUtil.getExpirationDateFromToken(_ as String) >> Date.from(Instant.now().plusSeconds(100))

        and: "Um donation válido"
        def donationRequest =
                DonationRequest.builder()
                        .token("1")
                        .doador("doador")
                        .item("item")
                        .quantidade(1d)
                        .pontos(1l)
                        .code(1)
                        .build()

        and: "Uma escola válida"
        Escola escola = new Escola(1l, "Escola", "1", "Endereco", 1)
        repositorySchool.findEscolaByToken(_ as String)
                >> { escola }

        and: "Uma doação válida"
        repositoryDonation.findById(donationRequest.code)
                >> { Doacao.builder().item("item").pontos(1).doador("doador").quantidade(1).escola(escola).build() }

        when: "O método é executado"
        def response = donationUsecase.selectDonation(String.valueOf(donationRequest.code), "1")

        then: "Deve retornar um response válido"
        verifyAll(response as DonationResponse) {
            it.item == "item"
            it.quantidade == 1
            it.doador == "doador"
        }
    }

    def "Dado um auth válido, deve retornar um response valido após selecionar todas as Donation"() {
        given: "Um JWT dentro da válidade"
        jwtTokenUtil.getExpirationDateFromToken(_ as String) >> Date.from(Instant.now().plusSeconds(100))

        and: "Um donation válido"
        def donationRequest =
                DonationRequest.builder()
                        .token("1")
                        .doador("doador")
                        .item("item")
                        .quantidade(1d)
                        .pontos(1l)
                        .code(1)
                        .build()

        and: "Uma escola válida"
        Escola escola = new Escola(1l, "Escola", "1", "Endereco", 1)
        repositorySchool.findEscolaByToken(_ as String)
                >> { escola }

        and: "Uma doação válida"
        repositoryDonation.findAllByEscola(escola)
                >> { List.of(Doacao.builder().item("item")
                                             .pontos(1).doador("doador")
                                             .quantidade(1).escola(escola).build()) }

        when: "O método é executado"
        def response = donationUsecase.allDonations("1")

        then: "Deve retornar um response válido"
        verifyAll(response as DonationAllResponse) {
            verifyAll(it.donations[0] as DonationResponse){
                it.item == "item"
                it.quantidade == 1
                it.doador == "doador"
            }
        }
    }

    def "Dado um auth expirado, deve retornar uma exception"() {
        given: "Um JWT dentro da válidade"
        jwtTokenUtil.getExpirationDateFromToken(_ as String) >> Date.from(Instant.now().minusMillis(100))

        when: "O método é executado"
        def response = donationUsecase.selectDonation("1", _ as String)

        then: "Deve retornar um response válido"
        thrown(InvalidTokenException)
    }
}