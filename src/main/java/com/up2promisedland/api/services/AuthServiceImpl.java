package com.up2promisedland.api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.up2promisedland.api.beans.LoginBean;
import com.up2promisedland.api.entities.Usuario;
import com.up2promisedland.api.repositories.UsuarioRepository;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Integer login(LoginBean loginBean) {
		Optional<Usuario> usuario = usuarioRepository.findByCorreoElectronico(loginBean.getCorreoElectronico());

		if (!usuario.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");

		if (!usuario.get().getContrasena().equals(loginBean.getContrasena()))
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Password not match");

		return usuario.get().getId();
	}

}
