package com.up2promisedland.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.up2promisedland.api.entities.Producto;
import com.up2promisedland.api.services.ProductoService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/producto")
public class ProductoController {

	@Autowired
	private ProductoService productoService;

	@GetMapping("/{id}")
	public ResponseEntity<Producto> findByTipo(@PathVariable Integer id) {
		return new ResponseEntity<Producto>(productoService.findById(id), HttpStatus.OK);
	}

	@GetMapping("/findByTipo")
	public ResponseEntity<List<Producto>> findByTipo(@RequestParam String tipo) {
		return new ResponseEntity<List<Producto>>(productoService.findByTipo(tipo), HttpStatus.OK);
	}

	@GetMapping("/findByNivel")
	public ResponseEntity<List<Producto>> findByNivel(@RequestParam Integer nivel) {
		return new ResponseEntity<List<Producto>>(productoService.findByNivel(nivel), HttpStatus.OK);
	}

}
