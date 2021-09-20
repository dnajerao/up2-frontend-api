package com.up2promisedland.api.beans;

import java.util.List;

import com.up2promisedland.api.entities.Direccion;

import lombok.Data;

@Data
public class PaymentWrapper {
	
	private Direccion direccion;
	
	private List<PaymentProductWrapper> productos;
	
	private boolean msi;
	
	private PaymentInvoiceWrapper facturacion;
	
	private String conektaToken;

}
