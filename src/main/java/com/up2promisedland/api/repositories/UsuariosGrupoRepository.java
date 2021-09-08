package com.up2promisedland.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.up2promisedland.api.entities.Grupo;
import com.up2promisedland.api.entities.UsuariosGrupo;

public interface UsuariosGrupoRepository extends JpaRepository<UsuariosGrupo, Integer>{
	
	List<UsuariosGrupo> findByGrupo(Grupo grupo);

}
