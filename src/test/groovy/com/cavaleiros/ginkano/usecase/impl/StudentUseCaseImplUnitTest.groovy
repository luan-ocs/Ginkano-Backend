package com.cavaleiros.ginkano.usecase.impl

import com.cavaleiros.ginkano.config.JwtTokenUtil
import com.cavaleiros.ginkano.core.domain.dto.Student
import com.cavaleiros.ginkano.core.entity.Aluno
import com.cavaleiros.ginkano.core.entity.Sala
import com.cavaleiros.ginkano.exception.InvalidTokenException
import com.cavaleiros.ginkano.repository.RepositoryStudent
import spock.lang.Specification
import spock.lang.Subject

import java.time.Instant

class StudentUseCaseImplUnitTest extends Specification {

    private JwtTokenUtil jwtTokenUtil = Mock()
    private RepositoryStudent repositoryStudent = Mock()

    @Subject
    private StudentUseCaseImpl studentUseCase = new StudentUseCaseImpl(jwtTokenUtil, repositoryStudent)

    def "Dado um auth válido, deve retornar todos os alunos cadastrado"() {
        given: "Um JWT dentro da válidade"
        jwtTokenUtil.getExpirationDateFromToken(_ as String) >> Date.from(Instant.now().plusSeconds(100))

        and: "Uma escola válida"
        1 * repositoryStudent.findAll()
                >> { List.of(new Aluno("123", new Sala("sala","num_sala"))) }

        when: "O método é executado"
        def response = studentUseCase.execute("auth")

        then: "Deve retornar um response válido"
        verifyAll(response.data[0].student as Student) {
            it.matricula == "123"
            verifyAll(it.sala as Sala) {
                it.descricao == "sala"
                it.numSala == "num_sala"
            }
        }
    }

    def "Dado um auth válido, deve retornar um aluno cadastrado"() {
        given: "Um JWT dentro da válidade"
        jwtTokenUtil.getExpirationDateFromToken(_ as String) >> Date.from(Instant.now().plusSeconds(100))

        and: "Uma escola válida"
        1 * repositoryStudent.findStudentByMatricula("1")
                >> { new Aluno("123", new Sala("sala","num_sala")) }

        when: "O método é executado"
        def response = studentUseCase.execute("1", "auth")

        then: "Deve retornar um response válido"
        verifyAll(response.student as Student) {
            it.matricula == "123"
            verifyAll(it.sala as Sala) {
                it.descricao == "sala"
                it.numSala == "num_sala"
            }
        }
    }

    def "Dado um auth expirado, deve retornar uma exception"() {
        given: "Um JWT dentro da válidade"
        jwtTokenUtil.getExpirationDateFromToken(_ as String) >> Date.from(Instant.now().minusMillis(100))

        when: "O método é executado"
        def response = studentUseCase.execute("1", _ as String)

        then: "Deve retornar um response válido"
        thrown(InvalidTokenException)
    }

}
