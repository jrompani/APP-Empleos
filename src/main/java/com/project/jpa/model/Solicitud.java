/**
 * Esta clase representa una entidad (un registro) en la tabla de Solicitudes de la base de datos
 */
package com.project.jpa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Solicitudes")
public class Solicitud {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer id;
	@Column
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date fecha;
	@Column
	private String comentarios;
	@Column
	private String archivo;

	@OneToOne
	@JoinColumn(name = "idVacante")
	private Vacante vacante;

	@OneToOne
	@JoinColumn(name = "idUsuario") 
	private Usuario usuario;

	public Solicitud() {

	}

	public Solicitud(Date fecha) {
		this.fecha = new Date();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	public Vacante getVacante() {
		return vacante;
	}

	public void setVacante(Vacante vacante) {
		this.vacante = vacante;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	@Override
	public String toString() {
		return "Solicitud [id=" + id + ", fecha=" + fecha + ", comentarios=" + comentarios + ", archivo=" + archivo
				+ ", vacante=" + vacante + ", usuario=" + usuario + "]";
	}

}
