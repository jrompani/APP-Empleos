package com.project.jpa.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.project.jpa.model.Vacante;

public interface IVacantesService {
	void guardar(Vacante vacante);
	void eliminar(Long idVacante);
	List<Vacante> buscarTodas();
	Vacante buscarPorId(Long idVacante);
	List<Vacante> buscarDestacadas();
	Page<Vacante> buscarTodas(Pageable page);
	List<Vacante> buscarByExample(Example<Vacante> example);
}
