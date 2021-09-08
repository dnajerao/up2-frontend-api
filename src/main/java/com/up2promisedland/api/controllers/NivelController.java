package com.up2promisedland.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.up2promisedland.api.entities.Nivel;
import com.up2promisedland.api.services.NivelService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/nivel")
public class NivelController {

	@Autowired
	private NivelService nivelService;

	@GetMapping
	public ResponseEntity<List<Nivel>> getAll() {
		return new ResponseEntity<List<Nivel>>(nivelService.getAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Nivel> getNivelById(@PathVariable Integer id) {
		return new ResponseEntity<Nivel>(nivelService.getNivelById(id), HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<Nivel> addNivel(@RequestBody Nivel Nivel) {
		return new ResponseEntity<Nivel>(nivelService.addNivel(Nivel), HttpStatus.OK);
	}

}
