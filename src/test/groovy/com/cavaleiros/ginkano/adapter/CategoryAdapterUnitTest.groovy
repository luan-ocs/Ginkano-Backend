package com.cavaleiros.ginkano.adapter

import com.cavaleiros.ginkano.core.domain.dto.Category
import com.cavaleiros.ginkano.core.domain.response.CategoryResponse
import com.cavaleiros.ginkano.core.entity.Categoria
import com.cavaleiros.ginkano.core.entity.Tipo
import spock.lang.Specification

class CategoryAdapterUnitTest extends Specification {

    def "Deve retornar um Category passando uma categoria como parámetro"() {
        given: "Um objeto Categoria válido"
        Categoria categoria = new Categoria("Descricao", new Tipo("Tipo"))

        when:"O adapter é chamado"
        Category category = CategoryAdapter.toCategoria(categoria)

        then:"Retornar um objeto válido"
        verifyAll(category as Category){
            it.descricao == "Descricao"
            it.type.descricao == "Tipo"
        }
    }

    def "Deve retornar um CategoryResponse passando uma categoria como parámetro"() {
        given: "Um objeto Categoria válido"
        Categoria categoria = new Categoria("Descricao", new Tipo("Tipo"))

        when:"O adapter é chamado"
        CategoryResponse categoryResponse = CategoryAdapter.toCategoryResponse(categoria)

        then:"Retornar um objeto válido"
        verifyAll(categoryResponse.category as Category){
            it.descricao == "Descricao"
            it.type.descricao == "Tipo"
        }
    }
}
