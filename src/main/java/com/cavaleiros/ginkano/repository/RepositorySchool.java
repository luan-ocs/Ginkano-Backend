package com.cavaleiros.ginkano.repository;

import com.cavaleiros.ginkano.core.entity.Escola;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositorySchool extends JpaRepository<Escola, Integer> {

    Escola findEscolaByToken(String token);

    List<Escola> findAllByAtivo(Integer ativo);
}
