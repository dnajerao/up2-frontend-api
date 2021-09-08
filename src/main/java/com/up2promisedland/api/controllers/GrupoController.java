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

import com.up2promisedland.api.beans.UsuariosGrupoWrapper;
import com.up2promisedland.api.entities.Grupo;
import com.up2promisedland.api.entities.Usuario;
import com.up2promisedland.api.entities.UsuariosGrupo;
import com.up2promisedland.api.services.GrupoService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/grupo")
public class GrupoController {

	@Autowired
	private GrupoService grupoService;

	@GetMapping
	public ResponseEntity<List<Grupo>> getAll() {
		return new ResponseEntity<List<Grupo>>(grupoService.getAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Grupo> getGrupoById(@PathVariable Integer id) {
		return new ResponseEntity<Grupo>(grupoService.getGrupoById(id), HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<Grupo> addGrupo(@RequestBody Grupo grupo) {
		return new ResponseEntity<Grupo>(grupoService.addGrupo(grupo), HttpStatus.OK);
	}
	
	@GetMapping("/usuariosWOgrupo")
	public ResponseEntity<List<Usuario>> getUsuariosWOGrupo() {
		return new ResponseEntity<List<Usuario>>(grupoService.getUsuariosWOGrupo(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}/usuarios")
	public ResponseEntity<List<UsuariosGrupo>> getUsuariosByGrupoId(@PathVariable Integer id) {
		return new ResponseEntity<List<UsuariosGrupo>>(grupoService.getUsuariosByGrupoId(id), HttpStatus.OK);
	}
	
	@PostMapping("/{id}/usuarios")
	public ResponseEntity<List<UsuariosGrupo>> addUsuariosToGrupo(@PathVariable Integer id, @RequestBody UsuariosGrupoWrapper usuarios) {
		return new ResponseEntity<List<UsuariosGrupo>>(grupoService.addUsuariosToGroupId(id, usuarios), HttpStatus.OK);
	}

}
