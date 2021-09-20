package com.up2promisedland.api.services;

import com.up2promisedland.api.conekta.ConektaCharge;
import com.up2promisedland.api.entities.OrdenPago;

import io.conekta.Charge;

public interface ConektaService {

	public Charge createCardPayment(OrdenPago paymentOrder, String token, Boolean msi);

	public ConektaCharge parseCharge(OrdenPago payment, Boolean msi, String token);

}
