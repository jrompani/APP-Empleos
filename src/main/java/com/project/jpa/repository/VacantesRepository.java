package com.project.jpa.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.jpa.model.Vacante;


public interface VacantesRepository extends JpaRepository<Vacante, Long> {
	
	List<Vacante> findByStatus(String status);
	
	List<Vacante> findByDestacadoAndStatusOrderByIdDesc(int destacado, String status);
	
	List<Vacante> findBySalarioBetweenOrderBySalarioDesc(double s1, double s2);
	
	List<Vacante> findByStatusIn(String[] estatus);

}
