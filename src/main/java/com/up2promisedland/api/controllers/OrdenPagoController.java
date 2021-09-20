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

import com.up2promisedland.api.beans.Filtros;
import com.up2promisedland.api.beans.PaymentWrapper;
import com.up2promisedland.api.entities.OrdenPago;
import com.up2promisedland.api.entities.Pago;
import com.up2promisedland.api.services.OrdenPagoService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/pago")
public class OrdenPagoController {

	@Autowired
	private OrdenPagoService ordenPagoService;

	@GetMapping("/{id}")
	public ResponseEntity<OrdenPago> getPagos(@PathVariable Integer id) {
		return new ResponseEntity<OrdenPago>(ordenPagoService.getById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Pago> pay(@RequestBody PaymentWrapper pago) {
		return new ResponseEntity<Pago>(ordenPagoService.pay(pago), HttpStatus.OK);
	}

	@PostMapping("/buscarPorFiltros")
	public ResponseEntity<List<OrdenPago>> getPagos(@RequestBody Filtros filtros) {
		return new ResponseEntity<List<OrdenPago>>(ordenPagoService.getPagos(filtros), HttpStatus.OK);
	}

}
