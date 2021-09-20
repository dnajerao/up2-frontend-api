package com.up2promisedland.api.services;

import java.util.List;

import com.up2promisedland.api.entities.Producto;

public interface ProductoService {

	public Producto findById(Integer id);

	public List<Producto> findByTipo(String tipo);

	public List<Producto> findByNivel(Integer id);

}
