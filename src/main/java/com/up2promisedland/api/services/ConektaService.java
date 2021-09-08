package com.up2promisedland.api.services;

import com.up2promisedland.api.entities.Usuario;

import io.conekta.ConektaList;
import io.conekta.Customer;
import io.conekta.Error;
import io.conekta.ErrorList;
import io.conekta.Order;

public interface ConektaService {
	
	public Usuario createCustomer(Usuario usuario);
	
	public ConektaList createCardPayment();

}
