package com.up2promisedland.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.up2promisedland.api.entities.Rol;
import com.up2promisedland.api.entities.Usuario;
import com.up2promisedland.api.repositories.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Usuario addUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public List<Usuario> getUsuarios() {
		return usuarioRepository.findAll();
	}

	@Override
	public Usuario getUsuarioById(Integer id) {
		return usuarioRepository.getById(id);
	}

	@Override
	public List<Usuario> getUsuarioByRol(Integer id) {
		return usuarioRepository.findByRol(new Rol(id));
	}
	

}
