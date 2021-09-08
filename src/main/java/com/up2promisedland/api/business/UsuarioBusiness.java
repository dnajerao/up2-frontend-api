package com.up2promisedland.api.business;

import java.util.List;

import com.up2promisedland.api.entities.Usuario;

public interface UsuarioBusiness {
	
	public Usuario addUsuario(Usuario usuario);
	
	public List<Usuario> getUsuarios();
	
	public Usuario getUsuarioById(Integer id);

}
