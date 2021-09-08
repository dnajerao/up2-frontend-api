package com.up2promisedland.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.up2promisedland.api.entities.Rol;
import com.up2promisedland.api.services.RolService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/rol")
public class RolController {

	@Autowired
	private RolService rolService;

	@GetMapping
	public ResponseEntity<List<Rol>> getUsuarios() {
		return new ResponseEntity<List<Rol>>(rolService.getAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Rol> getUsuarioById(@PathVariable Integer id) {
		return new ResponseEntity<Rol>(rolService.getRolById(id), HttpStatus.OK);
	}

}
