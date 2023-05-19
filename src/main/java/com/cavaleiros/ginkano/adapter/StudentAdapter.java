package com.cavaleiros.ginkano.adapter;

import com.cavaleiros.ginkano.core.domain.dto.Student;
import com.cavaleiros.ginkano.core.domain.response.StudentResponse;
import com.cavaleiros.ginkano.core.entity.Aluno;

public class StudentAdapter {
    public static Student toStudent(Aluno aluno){
        return Student.builder()
                .matricula(aluno.getMatricula())
                .sala(aluno.getSala())
                .build();
    }

    public static StudentResponse toStudentResponse(Aluno aluno){
        return StudentResponse.builder()
                .student(toStudent(aluno))
                .build();
    }
}
