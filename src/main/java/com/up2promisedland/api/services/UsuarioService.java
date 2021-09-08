package com.up2promisedland.api.services;

import java.util.List;

import com.up2promisedland.api.entities.Usuario;

public interface UsuarioService {
	
	public Usuario addUsuario(Usuario usuario);
	
	public List<Usuario> getUsuarios();
	
	public Usuario getUsuarioById(Integer id);
	
	public List<Usuario> getUsuarioByRol(Integer id);

}
