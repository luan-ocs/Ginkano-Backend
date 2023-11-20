package com.cavaleiros.ginkano.adapter

import com.cavaleiros.ginkano.core.domain.request.DonationRequest
import com.cavaleiros.ginkano.core.domain.response.DonationAllResponse
import com.cavaleiros.ginkano.core.domain.response.DonationResponse
import com.cavaleiros.ginkano.core.entity.Doacao
import com.cavaleiros.ginkano.core.entity.Escola
import spock.lang.Specification

class DonationAdapterUnitTest extends Specification {

    def "Deve retornar um Doacao passando DonationRequest como parámetro"() {
        given:"Um objeto escola válido"
        Escola escola = new Escola(1, "nome", "token","endereco", 1)

        and: "Um objeto DonationRequest válido"
        DonationRequest donationRequest =
                DonationRequest.builder()
                        .item("item")
                        .doador("doador")
                        .quantidade(1)
                        .pontos(1)
                        .build();

        when:"O adapter é chamado"
        Doacao doacao = DonationAdapter.toEntity(donationRequest, escola)

        then:"Retornar um objeto válido"
        verifyAll(doacao as Doacao){
            it.doador == "doador"
            it.item == "item"
        }
    }

    def "Deve retornar um DonationResponse passando DonationRequest como parámetro"() {
        given:"Um objeto escola válido"
        Escola escola = new Escola(1, "nome", "token","endereco", 1)

        and: "Um objeto DonationRequest válido"
        DonationRequest donationRequest =
                DonationRequest.builder()
                        .item("item")
                        .doador("doador")
                        .quantidade(1)
                        .pontos(1)
                        .build();

        when:"O adapter é chamado"
        DonationResponse doacao = DonationAdapter.fromDonationResponse(donationRequest, escola)

        then:"Retornar um objeto válido"
        verifyAll(doacao as DonationResponse){
            it.doador == "doador"
            it.item == "item"
        }
    }

    def "Deve retornar um DonationResponse passando Doacao como parámetro"() {
        given:"Um objeto escola válido"
        Escola escola = new Escola(1, "nome", "token","endereco", 1)

        and: "Um objeto DonationRequest válido"
        Doacao doacao =
                Doacao.builder()
                        .id(1l)
                        .item("item")
                        .doador("doador")
                        .quantidade(1)
                        .pontos(1)
                        .escola(escola)
                        .build();

        when:"O adapter é chamado"
        DonationResponse donationResponse = DonationAdapter.fromDonationRequest(doacao)

        then:"Retornar um objeto válido"
        verifyAll(donationResponse as DonationResponse){
            it.doador == "doador"
            it.item == "item"
        }
    }

    def "Deve retornar uma lista de Donations passando uma lista como parámetro"() {
        given:"Um objeto escola válido"
        Escola escola = new Escola(1, "nome", "token","endereco", 1)

        and: "Uma lista de Doacoes válida"
        List<Doacao> doacoes = List.of(
                Doacao.builder()
                        .id(1l)
                        .item("item")
                        .doador("doador")
                        .quantidade(1)
                        .pontos(1)
                        .escola(escola)
                        .build())

        when:"O adapter é chamado"
        DonationAllResponse donationAllResponse = DonationAdapter.fromDonationAllResponse(doacoes)

        then:"Retornar um objeto válido"
        verifyAll(donationAllResponse as DonationAllResponse){
            it.donations[0].doador == "doador"
            it.donations[0].item == "item"
        }
    }
}
