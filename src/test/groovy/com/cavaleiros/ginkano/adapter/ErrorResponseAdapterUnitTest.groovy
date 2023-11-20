package com.cavaleiros.ginkano.adapter

import com.cavaleiros.ginkano.core.domain.request.DonationRequest
import com.cavaleiros.ginkano.core.domain.response.error.ErrorResponse
import com.cavaleiros.ginkano.core.entity.Doacao
import com.cavaleiros.ginkano.core.entity.Escola
import spock.lang.Specification

class ErrorResponseAdapterUnitTest extends Specification {

    def "Deve retornar um ErrorResponse"() {
        given: "Um objeto Exception válido"
        Exception e = new Exception("Error")

        when: "O adapter é chamado"
        ErrorResponse errorResponse = ErrorResponseAdapter.toErrorResponse(e, "400")

        then: "Retornar um objeto válido"
        verifyAll(errorResponse as ErrorResponse) {
            it.error.detail == "Error"
            it.error.code == "400"
            it.error.title == "Ocorreu um erro"
        }
    }
}