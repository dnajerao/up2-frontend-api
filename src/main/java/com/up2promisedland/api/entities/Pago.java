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
public class Pago {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "ordenPago")
	private OrdenPago ordenPago;

	private String conektaOrderId;

	private String conektaCargoId;

	private String razonSocial;

	private String rfc;

	private String estatus;

	@Column(insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date fechaRegistro;

	@Column(insertable = false, updatable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date fechaActualizacion;

	public Pago(Integer id) {
		super();
		this.id = id;
	}

	public Pago(OrdenPago ordenPago, String conektaOrderId, String conektaCargoId) {
		super();
		this.ordenPago = ordenPago;
		this.conektaOrderId = conektaOrderId;
		this.conektaCargoId = conektaCargoId;
	}

	public Pago(OrdenPago ordenPago, String conektaOrderId, String conektaCargoId, String estatus) {
		super();
		this.ordenPago = ordenPago;
		this.conektaOrderId = conektaOrderId;
		this.conektaCargoId = conektaCargoId;
		this.estatus = estatus;
	}

}
