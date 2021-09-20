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

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class CarritoProductos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ordenPago", nullable = false)
	private OrdenPago ordenPago;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "producto", nullable = false)
	private Producto producto;

	private Integer cantidad;

	@Column(insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date fechaRegistro;

	@Column(insertable = false, updatable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date fechaActualizacion;

	public CarritoProductos(Integer id) {
		super();
		this.id = id;
	}

	public CarritoProductos(OrdenPago ordenPago, Producto producto, Integer cantidad) {
		super();
		this.ordenPago = ordenPago;
		this.producto = producto;
		this.cantidad = cantidad;
	}

}
