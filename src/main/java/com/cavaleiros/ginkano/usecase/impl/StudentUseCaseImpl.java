package com.cavaleiros.ginkano.usecase.impl;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cavaleiros.ginkano.usecase.StudentUsecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.cavaleiros.ginkano.adapter.StudentAdapter;
import com.cavaleiros.ginkano.config.JwtTokenUtil;
import com.cavaleiros.ginkano.core.domain.response.StudentAllResponse;
import com.cavaleiros.ginkano.core.domain.response.StudentResponse;
import com.cavaleiros.ginkano.core.entity.Aluno;
import com.cavaleiros.ginkano.exception.InvalidTokenException;
import com.cavaleiros.ginkano.repository.RepositoryStudent;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentUseCaseImpl implements StudentUsecase{
    private final JwtTokenUtil jwtTokenUtil;
    private final RepositoryStudent repositoryStudent;

    @Override
    public StudentAllResponse execute(String auth) throws InvalidTokenException {
        validatedToken(auth);

        List<Aluno> alunos = repositoryStudent.findAll();
        List<StudentResponse> students = new ArrayList<>();

        alunos.forEach(a -> students.add(StudentAdapter.toStudentResponse(a)));

        return StudentAllResponse.builder()
                .data(students)
                .localDate(LocalDate.now())
                .build();
    }

    @Override
    public StudentResponse execute(String token, String auth) throws InvalidTokenException {
        validatedToken(auth);

        Aluno aluno = repositoryStudent.findStudentByMatricula(token);
        return StudentResponse.builder().student(StudentAdapter.toStudent(aluno)).build();
    }

    public void validatedToken(String auth) throws InvalidTokenException {
        Date expirationDate = jwtTokenUtil.getExpirationDateFromToken(auth);
        if(expirationDate.before(Date.from(Instant.now()))){
            throw new InvalidTokenException("Token expirado ou invalido");
        }
    }
}
