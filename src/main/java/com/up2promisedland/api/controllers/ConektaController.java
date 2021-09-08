package com.up2promisedland.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.up2promisedland.api.entities.Usuario;
import com.up2promisedland.api.services.ConektaService;

import io.conekta.ConektaList;
import io.conekta.Customer;
import io.conekta.Order;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/conekta")
public class ConektaController {

	@Autowired
	private ConektaService conektaService;

	@PostMapping("/cliente")
	public ResponseEntity<Usuario> createCustomer() {
		return new ResponseEntity<Usuario>(conektaService.createCustomer(new Usuario()), HttpStatus.OK);
	}

	@PostMapping("/pagotarjeta")
	public ResponseEntity<ConektaList> createCardPayment() {
		return new ResponseEntity<ConektaList>(conektaService.createCardPayment(), HttpStatus.OK);
	}

}
