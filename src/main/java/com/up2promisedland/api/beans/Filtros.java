package com.up2promisedland.api.beans;

import java.util.Date;

import lombok.Data;

@Data
public class Filtros {

	private String tipo;

	private String[] valores;
	
	private String[] estatus;

	private Date fechaInicio;

	private Date fechaFin;

}