package com.cavaleiros.ginkano.repository;

import com.cavaleiros.ginkano.core.entity.Doacao;
import com.cavaleiros.ginkano.core.entity.Escola;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositoryDonation extends JpaRepository<Doacao, Integer> {

    Doacao findById(Long code);
    List<Doacao> findAllByEscola(Escola escola);
}
