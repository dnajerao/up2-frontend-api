package com.up2promisedland.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.up2promisedland.api.entities.Contacto;
import com.up2promisedland.api.entities.Examen;
import com.up2promisedland.api.services.ContactoService;
import com.up2promisedland.api.services.ExamenService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/examen")
public class ExamenController {

	@Autowired
	private ExamenService examenService;

	@PostMapping
	public Examen addExamen(@RequestBody Examen examen) {
		return examenService.addExamen(examen);
	}

}
