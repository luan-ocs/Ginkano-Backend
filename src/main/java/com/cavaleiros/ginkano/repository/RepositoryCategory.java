package com.cavaleiros.ginkano.repository;
import com.cavaleiros.ginkano.core.entity.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryCategory extends JpaRepository<Categoria, Integer>{
    
    Categoria findCategoriaById(Integer id);
}
