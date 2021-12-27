
package com.project.jpa.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment MySQL
	private Long id;
	@Column
	private String username;
	@Column
	private String nombre;
	@Column
	private String email;
	@Column
	private String password;
	@Column
	private Integer status;
	@Column
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date fechaRegistro;
	

	// Relacion ManyToMany (Un usuario tiene muchos perfiles)
	// Por defecto Fetch es FetchType.LAZY
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "UsuarioPerfil", // tabla intermedia
			joinColumns = @JoinColumn(name = "idUsuario"), // foreignKey en la tabla de UsuarioPerfil
			inverseJoinColumns = @JoinColumn(name = "idPerfil") // foreignKey en la tabla de UsuarioPerfil
	)
	private List<Perfil> perfiles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public List<Perfil> getPerfiles() {
		return perfiles;
	}

	public void setPerfiles(List<Perfil> perfiles) {
		this.perfiles = perfiles;
	}
	
	// Metodo para agregar perfiles
	public void agregar(Perfil tempPerfil) {
		if (perfiles == null) {
			perfiles = new LinkedList<>();
		}
		perfiles.add(tempPerfil);
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", username=" + username + ", nombre=" + nombre + ", email=" + email
				+ ", password=" + password + ", status=" + status + ", fechaRegistro=" + fechaRegistro + ", perfiles="
				+ perfiles + "]";
	}
	
}
