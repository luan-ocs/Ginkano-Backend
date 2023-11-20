package com.cavaleiros.ginkano.adapter

import com.cavaleiros.ginkano.core.domain.dto.Prize
import com.cavaleiros.ginkano.core.domain.response.PrizeResponse
import com.cavaleiros.ginkano.core.entity.Premio
import spock.lang.Specification

class PrizeAdapterUnitTest extends Specification {

    def "Deve retornar um Prize válido"(){
        given: "Um objeto Premio válido"
        Premio premio = new Premio("Premio", "Descricao")

        when:"O método de adapter é chamado"
        Prize prize = PrizeAdapter.toPrize(premio)

        then:"deve retornar um objeto Group válido"
        verifyAll(prize){
            it.nome == "Premio"
            it.descricao == "Descricao"
        }
    }

    def "Deve retornar um PrizeResponse válido"(){
        given: "Um objeto Premio válido"
        Premio premio = new Premio("Premio", "Descricao")

        when:"O método de adapter é chamado"
        PrizeResponse prizeResponse = PrizeAdapter.toPrizeResponse(premio)

        then:"deve retornar um objeto Group válido"
        verifyAll(prizeResponse.prize){
            it.nome == "Premio"
            it.descricao == "Descricao"
        }
    }
}
