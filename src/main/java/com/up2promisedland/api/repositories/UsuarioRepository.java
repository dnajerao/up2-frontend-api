package com.up2promisedland.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.up2promisedland.api.entities.Rol;
import com.up2promisedland.api.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	public Optional<Usuario> findByCorreoElectronico(String correoElectronico);
	
	public List<Usuario> findByRol(Rol rol);
	
	@Query("SELECT u FROM Usuario u WHERE u NOT IN (SELECT alumno FROM UsuariosGrupo WHERE estatus = 'Activo')")
	public List<Usuario> findWithoutGroup();

}
