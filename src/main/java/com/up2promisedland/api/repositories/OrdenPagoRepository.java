package com.up2promisedland.api.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.up2promisedland.api.entities.CarritoProductos;
import com.up2promisedland.api.entities.OrdenPago;
import com.up2promisedland.api.entities.Usuario;

public interface OrdenPagoRepository extends JpaRepository<OrdenPago, Integer> {

	List<OrdenPago> findByFechaLimiteBetweenOrderByFechaLimite(Date fechaInicio, Date fechaFin);

	List<OrdenPago> findByEstatusInAndFechaLimiteBetweenOrderByFechaLimite(String[] estatus, Date fechaInicio,
			Date fechaFin);

	List<OrdenPago> findByUsuarioInAndFechaLimiteBetweenOrderByFechaLimite(List<Usuario> usuario, Date fechaInicio,
			Date fechaFin);

	List<OrdenPago> findByUsuarioInAndEstatusInAndFechaLimiteBetweenOrderByFechaLimite(List<Usuario> usuario,
			String[] estatus, Date fechaInicio, Date fechaFin);

	List<OrdenPago> findByCarritoAndFechaLimiteBetweenOrderByFechaLimite(CarritoProductos carrito, Date fechaInicio,
			Date fechaFin);

	List<OrdenPago> findByCarritoAndEstatusInAndFechaLimiteBetweenOrderByFechaLimite(CarritoProductos carrito,
			String[] estatus, Date fechaInicio, Date fechaFin);

	@Query("SELECT p FROM OrdenPago p JOIN p.usuario.usuarioResponsable u "
			+ "WHERE u IN ?1 AND p.fechaLimite >= ?2 AND p.fechaLimite <= ?3 ORDER BY p.fechaLimite")
	List<OrdenPago> findByUsuarioResponsableAndFechaLimiteBetween(List<Usuario> usuario, Date fechaInicio,
			Date fechaFin);

	@Query("SELECT p FROM OrdenPago p JOIN p.usuario.usuarioResponsable u "
			+ "WHERE u IN ?1 AND p.estatus IN ?2 AND p.fechaLimite >= ?3 AND p.fechaLimite <= ?4 ORDER BY p.fechaLimite")
	List<OrdenPago> findByUsuarioResponsableAndEstatusAndFechaLimiteBetween(List<Usuario> usuario, String[] estatus,
			Date fechaInicio, Date fechaFin);

}
