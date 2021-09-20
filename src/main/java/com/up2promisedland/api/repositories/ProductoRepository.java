package com.up2promisedland.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.up2promisedland.api.entities.Nivel;
import com.up2promisedland.api.entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

	public List<Producto> findByTipo(String tipo);

	public List<Producto> findByNivel(Nivel nivel);

	public Producto findByProductoAndNivel(String producto, Nivel nivel);

	public List<Producto> findByTipoAndNivel(String tipo, Nivel nivel);

}
