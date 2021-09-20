package com.up2promisedland.api.entities;

import java.util.Date;

import javax.persistence.CascadeType;
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
public class Direccion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario", nullable = true)
	private Usuario usuario;

	private String calle;

	private String numeroExterior;

	private String numeroInterior;

	private String colonia;

	private String delegacion;

	private String estado;

	private Integer codigoPostal;

	private String entreCalle1;

	private String entreCalle2;

	private String observacion;

	@Column(insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date fechaRegistro;

	@Column(insertable = false, updatable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date fechaActualizacion;

	public Direccion(Integer id) {
		super();
		this.id = id;
	}

	public String toMailString() {
		return calle
			+ ", " + (!numeroInterior.equals(null) ? "exterior " + numeroInterior.trim() : "exterior s/n")
			+ ", " + (!numeroInterior.equals(null) ? "interior " + numeroInterior.trim() : "interior s/n")
			+ ", " + colonia.trim()
			+ ", " + delegacion.trim()
			+ ", " + estado.trim()
			+ ", código postal " + codigoPostal
			+ ", entre calle " + entreCalle1.trim() + " y calle " + entreCalle2.trim()
			+ ", " + observacion.trim();
	}

}
