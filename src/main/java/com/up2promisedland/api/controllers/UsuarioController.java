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

import com.up2promisedland.api.entities.Usuario;
import com.up2promisedland.api.services.UsuarioService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping
	public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario usuario) {
		return new ResponseEntity<Usuario>(usuarioService.addUsuario(usuario), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Usuario>> getUsuarios() {
		return new ResponseEntity<List<Usuario>>(usuarioService.getUsuarios(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getUsuarioById(@PathVariable Integer id) {
		return new ResponseEntity<Usuario>(usuarioService.getUsuarioById(id), HttpStatus.OK);
	}
	
	@GetMapping("/byRol/{rolId}")
	public ResponseEntity<List<Usuario>> getUsuarioByRol(@PathVariable Integer rolId) {
		return new ResponseEntity<List<Usuario>>(usuarioService.getUsuarioByRol(rolId), HttpStatus.OK);
	}

}
