package com.up2promisedland.api.services;

import java.util.List;

import com.up2promisedland.api.entities.Usuario;
import com.up2promisedland.api.util.enums.Operation;

public interface UsuarioService {

	public Usuario addUsuario(Usuario usuario);

	public Usuario updateUsuario(Integer id, Usuario usuario);

	public List<Usuario> getUsuarios();

	public Usuario getUsuarioById(Integer id);

	public List<Usuario> getUsuarioByRol(Integer id);
	
	public List<Usuario> getBySupervisor(Integer id);

	public Usuario validateUsuario(Operation operation, Usuario usuario);
}
