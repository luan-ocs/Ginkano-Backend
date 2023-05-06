package com.cavaleiros.ginkano.repository;

import com.cavaleiros.ginkano.core.entity.Escola;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorySchool extends JpaRepository<Escola, Integer> {

    Escola findEscolaByToken(String token);
}
