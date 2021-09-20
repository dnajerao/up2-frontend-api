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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
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

	public Grupo(Integer id) {
		super();
		this.id = id;
	}

}