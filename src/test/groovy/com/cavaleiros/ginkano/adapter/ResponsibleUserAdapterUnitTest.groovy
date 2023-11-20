package com.cavaleiros.ginkano.adapter

import com.cavaleiros.ginkano.core.domain.dto.ResponsibleUser
import com.cavaleiros.ginkano.core.domain.request.RegisterUserRequest
import com.cavaleiros.ginkano.core.domain.request.UserRequest
import com.cavaleiros.ginkano.core.entity.Responsavel
import spock.lang.Specification

class ResponsibleUserAdapterUnitTest extends Specification {

    def "Deve retornar um ResponsibleUser válido"(){
        given: "Um objeto Responsavel válido"
        Responsavel responsavel = new Responsavel("novo", "novo_fisrt", "novo_last", 1,
                "novo_ocupacao", "novo_pass", 1)

        when:"O método de adapter é chamado"
        ResponsibleUser responsibleUser = ResponsibleUserAdapter.toResponsibleUser(responsavel)

        then:"deve retornar um objeto Group válido"
        verifyAll(responsibleUser){
            it.username == "novo"
            it.firstname =="novo_fisrt"
            it.lastname == "novo_last"
            it.ocupacao == "novo_ocupacao"
        }
    }

    def "Deve retornar um Responsavel válido"(){
        given: "Um objeto RegisterUserRequest válido"
        RegisterUserRequest registerUserRequest = RegisterUserRequest.builder()
                                                        .username("novo")
                                                        .password("123")
                                                        .firstname("first")
                                                        .lastname("last")
                                                        .conditions(1)
                                                        .ocupacao("ocupacao")
                                                        .build()

        when:"O método de adapter é chamado"
        Responsavel responsavel = ResponsibleUserAdapter.createrResponsavel(registerUserRequest)

        then:"deve retornar um objeto Group válido"
        verifyAll(responsavel){
            it.username == "novo"
            it.firstname =="first"
            it.lastname == "last"
            it.ocupacao == "ocupacao"
            it.password == "123"
            it.conditions == 1
            it.ocupacao == "ocupacao"
        }
    }

    def "Deve atualizar as informações presentes no objeto Responsável"(){
        given: "Um objeto Responsavel válido"
        Responsavel responsavel = new Responsavel("novo", "novo_first", "novo_last", 1,
                "novo_ocupacao", "novo_pass", 1)

        and:"Um objeto UserRequest válido"
        UserRequest user = UserRequest.builder()
                                .username("novo")
                                .firstname("first")
                                .lastname("last")
                                .conditions(1)
                                .ocupacao("ocupacao")
                                .build()

        when:"O método de adapter é chamado"
        Responsavel responsavel1 = ResponsibleUserAdapter.createrResponsavel(user, responsavel)

        then:"deve retornar um objeto Group válido"
        verifyAll(responsavel){
            it.username == "novo"
            it.firstname =="first"
            it.lastname == "last"
            it.ocupacao == "ocupacao"
        }
    }
}
