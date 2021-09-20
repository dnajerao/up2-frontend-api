package com.up2promisedland.api.services;

import java.util.List;

import com.up2promisedland.api.beans.UsuariosGrupoWrapper;
import com.up2promisedland.api.entities.Grupo;
import com.up2promisedland.api.entities.Usuario;
import com.up2promisedland.api.entities.UsuariosGrupo;
import com.up2promisedland.api.util.enums.Operation;

public interface GrupoService {

	public List<Grupo> getAll();

	public Grupo getGrupoById(Integer id);

	public Grupo addGrupo(Grupo grupo);

	public Grupo updateGrupo(Integer id, Grupo grupo);

	public List<Usuario> getUsuariosWOGrupo();

	public List<UsuariosGrupo> getUsuariosByGrupoId(Integer id);

	public List<UsuariosGrupo> addUsuariosToGroupId(Integer grupo, UsuariosGrupoWrapper usuarios);

	public Grupo validateGrupo(Operation operation, Grupo grupo);
}
