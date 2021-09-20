package com.up2promisedland.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.up2promisedland.api.entities.Nivel;
import com.up2promisedland.api.entities.Producto;
import com.up2promisedland.api.repositories.NivelRepository;
import com.up2promisedland.api.repositories.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoRepository productoRepository;

	@Autowired
	private NivelRepository nivelRepository;

	@Override
	public Producto findById(Integer id) {
		return productoRepository.findById(id).get();
	}

	@Override
	public List<Producto> findByTipo(String tipo) {
		return productoRepository.findByTipo(tipo);
	}

	@Override
	public List<Producto> findByNivel(Integer id) {

		Optional<Nivel> optNivel = nivelRepository.findById(id);
		if (!optNivel.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El nivel no existe");

		return productoRepository.findByNivel(optNivel.get());
	}

}
