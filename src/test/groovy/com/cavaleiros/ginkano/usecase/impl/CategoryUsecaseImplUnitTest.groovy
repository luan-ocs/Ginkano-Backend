package com.cavaleiros.ginkano.usecase.impl

import com.cavaleiros.ginkano.config.JwtTokenUtil
import com.cavaleiros.ginkano.core.domain.response.CategoryAllResponse
import com.cavaleiros.ginkano.core.domain.response.CategoryResponse
import com.cavaleiros.ginkano.core.entity.Categoria
import com.cavaleiros.ginkano.core.entity.Tipo
import com.cavaleiros.ginkano.exception.InvalidTokenException
import com.cavaleiros.ginkano.repository.RepositoryCategory
import spock.lang.Specification
import spock.lang.Subject

import java.time.Instant

class CategoryUsecaseImplUnitTest extends Specification {

    private JwtTokenUtil jwtTokenUtil = Mock()
    private RepositoryCategory repositoryCategory = Mock()

    @Subject
    private categoryUsecase = new CategoryUsecaseImpl(jwtTokenUtil, repositoryCategory)

    def "Dado um auth válido, deve retornar um response valido"() {
        given: "Um JWT dentro da válidade"
        jwtTokenUtil.getExpirationDateFromToken(_ as String) >> Date.from(Instant.now().plusSeconds(100))

        and: "Um retorno do reposity com retorno válido"
        repositoryCategory.findAll() >> {
            List.of(new Categoria("item", new Tipo("item")))
        }

        when: "O método é executado"
        def response = categoryUsecase.execute(_ as String)

        then: "Deve retornar um response válido"
        verifyAll(response as CategoryAllResponse) {
            verifyAll(it.data[0] as CategoryResponse) {
                it.category.descricao == "item"
            }
        }
    }

    def "Dado um auth válido, deve retornar um response valido a partir do token"() {
        given: "Um JWT dentro da válidade"
        jwtTokenUtil.getExpirationDateFromToken(_ as String) >> Date.from(Instant.now().plusSeconds(100))

        and: "Um retorno do reposity com retorno válido"
        repositoryCategory.findCategoriaById(_ as Integer) >> {
            new Categoria("item", new Tipo("item"))
        }

        when: "O método é executado"
        def response = categoryUsecase.execute("1", _ as String)

        then: "Deve retornar um response válido"
        verifyAll(response as CategoryResponse) {
            it.category.descricao == "item"
        }

    }

    def "Dado um auth expirado, deve retornar uma exception"() {
        given: "Um JWT dentro da válidade"
        jwtTokenUtil.getExpirationDateFromToken(_ as String) >> Date.from(Instant.now().minusMillis(100))

        when: "O método é executado"
        def response = categoryUsecase.execute("1", _ as String)

        then: "Deve retornar um response válido"
        thrown(InvalidTokenException)
    }
}