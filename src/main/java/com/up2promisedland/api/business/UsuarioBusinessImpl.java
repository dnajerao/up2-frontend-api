package com.up2promisedland.api.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.up2promisedland.api.entities.Usuario;
import com.up2promisedland.api.repositories.UsuarioRepository;

@Component
public class UsuarioBusinessImpl implements UsuarioBusiness{
	
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
		// TODO Auto-generated method stub
		return usuarioRepository.getById(id);
	}

}
