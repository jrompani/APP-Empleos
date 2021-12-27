package com.project.jpa.service;

import java.util.List;

import com.project.jpa.model.Usuario;


public interface IUsuariosService {
	void guardar(Usuario usuario);
	void eliminar(Long idUsuario);
	List<Usuario> buscarTodos();
	List<Usuario> buscarRegistrados();
	Usuario buscarPorId(Long idUsuario);
	Usuario buscarPorUsername(String username);
	int bloquear(Long idUsuario);
	int activar(Long idUsuario);
}
