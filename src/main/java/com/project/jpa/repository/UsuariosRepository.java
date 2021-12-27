package com.project.jpa.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.jpa.model.Usuario;


public interface UsuariosRepository extends JpaRepository<Usuario, Long> {
	// Buscar usuario por username
	Usuario findByUsername(String username);
	List<Usuario> findByFechaRegistroNotNull();
	
	@Modifying
    @Query("UPDATE Usuario u SET u.status=0 WHERE u.id = :paramIdUsuario")
    int lock(@Param("paramIdUsuario") Long idUsuario);
	
	@Modifying
    @Query("UPDATE Usuario u SET u.status=1 WHERE u.id = :paramIdUsuario")
    int unlock(@Param("paramIdUsuario") Long idUsuario);
	
}
