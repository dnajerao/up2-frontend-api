package com.up2promisedland.api.business;

import java.util.List;

import com.up2promisedland.api.entities.Grupo;

public interface GrupoBusiness {

	public List<Grupo> getAll();

	public Grupo getGrupoById(Integer id);

	public Grupo addGrupo(Grupo grupo);

}
