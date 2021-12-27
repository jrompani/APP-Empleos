package com.project.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.jpa.model.Solicitud;


public interface SolicitudesRepository extends JpaRepository<Solicitud, Integer> {

}
