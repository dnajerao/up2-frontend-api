package com.up2promisedland.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.up2promisedland.api.entities.Contacto;
import com.up2promisedland.api.services.ContactoService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/contacto")
public class ContactoController {

	@Autowired
	private ContactoService contactoService;

	@PostMapping
	public Contacto addContacto(@RequestBody Contacto contacto) {
		return contactoService.addContacto(contacto);
	}

}
