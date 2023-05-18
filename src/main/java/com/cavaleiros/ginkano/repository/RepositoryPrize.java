package com.cavaleiros.ginkano.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cavaleiros.ginkano.core.entity.Premio;

public interface RepositoryPrize extends JpaRepository<Premio, Integer>{
    Premio findPremioByNome(String nome);
}
