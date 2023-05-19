package com.cavaleiros.ginkano.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cavaleiros.ginkano.core.entity.Aluno;

public interface RepositoryStudent extends JpaRepository<Aluno, Integer>{

    Aluno findStudentByMatricula(String token);    
}
