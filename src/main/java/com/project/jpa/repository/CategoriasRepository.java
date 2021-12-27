package com.project.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.jpa.model.Categoria;


public interface CategoriasRepository extends JpaRepository<Categoria, Integer> {
	
}
