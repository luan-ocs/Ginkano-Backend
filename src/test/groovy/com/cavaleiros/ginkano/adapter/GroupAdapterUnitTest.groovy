package com.cavaleiros.ginkano.adapter

import com.cavaleiros.ginkano.core.domain.dto.Group
import com.cavaleiros.ginkano.core.domain.response.GroupResponse
import com.cavaleiros.ginkano.core.entity.Escola
import spock.lang.Specification

class GroupAdapterUnitTest extends Specification {

    def "Deve retornar um Group válido"(){
        given: "Um objeto Escola válido"
        Escola escola = Escola.builder().nome("Escola")
                                        .token("123")
                                        .endereco("Endereco")
                                        .build()

        when:"O método de adapter é chamado"
        Group group = GroupAdapter.toSchool(escola)

        then:"deve retornar um objeto Group válido"
        verifyAll(group){
            it.nome == "Escola"
            it.endereco == "Endereco"
            it.token == "123"
        }
    }

    def "Deve retornar um GroupResponse válido"(){
        given: "Um objeto Escola válido"
        Escola escola = Escola.builder().nome("Escola")
                .token("123")
                .endereco("Endereco")
                .build()

        when:"O método de adapter é chamado"
        GroupResponse groupResponse = GroupAdapter.toSchoolResponse(escola)

        then:"deve retornar um objeto Group válido"
        verifyAll(groupResponse.group){
            it.nome == "Escola"
            it.endereco == "Endereco"
            it.token == "123"
        }
    }

}
