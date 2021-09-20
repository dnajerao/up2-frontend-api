package com.up2promisedland.api.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
public class OrdenPago {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario", nullable = false)
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "direccion", nullable = true)
	private Direccion direccion;

	@OneToMany(mappedBy = "ordenPago", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<CarritoProductos> carrito;

	@Column(insertable = true, updatable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date fechaLimite;

	private String estatus;

	@Column(insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date fechaRegistro;

	@Column(insertable = false, updatable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date fechaActualizacion;

	public OrdenPago(Usuario usuario, Direccion direccion, List<CarritoProductos> carrito) {
		super();
		this.usuario = usuario;
		this.direccion = direccion;
		this.carrito = carrito;
	}

	public OrdenPago(Direccion direccion, List<CarritoProductos> carrito) {
		super();
		this.direccion = direccion;
		this.carrito = carrito;
	}

	public String carritoToMailString() {

		String mailString = "";

		for (CarritoProductos carrito : this.getCarrito()) {
			mailString.concat(carrito.getProducto().toMailString() + "<br>");
		}

		return mailString;
	}

}
