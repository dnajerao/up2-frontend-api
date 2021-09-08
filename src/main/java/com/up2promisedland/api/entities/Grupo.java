package com.up2promisedland.api.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Grupo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nombre;

	private String clave;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nivel", nullable = false)
	private Nivel nivel;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "profesor", nullable = false)
	private Usuario profesor;

	private String dias;

	private String horaInicio;

	private String horaFin;

	private Date fechaInicio;

	private Date fechaFin;

	private String link;

	@Column(insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date fechaRegistro;

	@Column(insertable = false, updatable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date fechaActualizacion;

	public Grupo() {
		super();
	}

	public Grupo(Integer id) {
		super();
		this.id = id;
	}

	public Grupo(String nombre, String clave, Nivel nivel, Usuario profesor, String dias, String horaInicio,
			String horaFin, Date fechaInicio, Date fechaFin, String link, Date fechaRegistro, Date fechaActualizacion) {
		super();
		this.nombre = nombre;
		this.clave = clave;
		this.nivel = nivel;
		this.profesor = profesor;
		this.dias = dias;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.link = link;
		this.fechaRegistro = fechaRegistro;
		this.fechaActualizacion = fechaActualizacion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

	public Usuario getProfesor() {
		return profesor;
	}

	public void setProfesor(Usuario profesor) {
		this.profesor = profesor;
	}

	public String getDias() {
		return dias;
	}

	public void setDias(String dias) {
		this.dias = dias;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grupo other = (Grupo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Grupo [id=" + id + ", nombre=" + nombre + ", clave=" + clave + ", nivel=" + nivel + ", profesor="
				+ profesor + ", dias=" + dias + ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + ", fechaInicio="
				+ fechaInicio + ", fechaFin=" + fechaFin + ", link=" + link + ", fechaRegistro=" + fechaRegistro
				+ ", fechaActualizacion=" + fechaActualizacion + "]";
	}

}