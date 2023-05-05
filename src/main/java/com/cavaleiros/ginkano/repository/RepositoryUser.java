package com.cavaleiros.ginkano.repository;


import com.cavaleiros.ginkano.core.entity.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryUser extends JpaRepository<Responsavel, Integer> {

    Responsavel findResponsavelByUsername(String username);
}
