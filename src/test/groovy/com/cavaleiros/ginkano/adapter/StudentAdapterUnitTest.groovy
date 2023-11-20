package com.cavaleiros.ginkano.adapter

import com.cavaleiros.ginkano.core.domain.dto.Prize
import com.cavaleiros.ginkano.core.domain.dto.Student
import com.cavaleiros.ginkano.core.domain.response.StudentResponse
import com.cavaleiros.ginkano.core.entity.Aluno
import com.cavaleiros.ginkano.core.entity.Sala
import spock.lang.Specification

class StudentAdapterUnitTest extends Specification {

    def "Deve retornar um Student válido"(){
        given: "Um objeto Aluno válido"
        Aluno aluno = new Aluno("123", new Sala("Sala", "100"))

        when:"O método de adapter é chamado"
        Student student = StudentAdapter.toStudent(aluno)

        then:"deve retornar um objeto Student válido"
        verifyAll(student){
            it.matricula == "123"
            it.sala.descricao == "Sala"
            it.sala.numSala == "100"
        }
    }

    def "Deve retornar um StudentResponse válido"(){
        given: "Um objeto Aluno válido"
        Aluno aluno = new Aluno("123", new Sala("Sala", "100"))

        when:"O método de adapter é chamado"
        StudentResponse studentResponse = StudentAdapter.toStudentResponse(aluno)

        then:"deve retornar um objeto Student válido"
        verifyAll(studentResponse.student){
            it.matricula == "123"
            it.sala.descricao == "Sala"
            it.sala.numSala == "100"
        }
    }
}
