package com.up2promisedland.api.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.up2promisedland.api.entities.Rol;
import com.up2promisedland.api.entities.Usuario;
import com.up2promisedland.api.repositories.RolRepository;
import com.up2promisedland.api.repositories.UsuarioRepository;
import com.up2promisedland.api.util.enums.Operation;
import com.up2promisedland.api.util.enums.RolEnum;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private RolRepository rolRepository;

	@Override
	public Usuario addUsuario(Usuario usuario) {
		return usuarioRepository.save(validateUsuario(Operation.CREATE, usuario));
	}

	@Override
	public Usuario updateUsuario(Integer id, Usuario usuario) {
		usuario.setId(id);
		return usuarioRepository.save(validateUsuario(Operation.UPDATE, usuario));
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
	
	@Override
	public List<Usuario> getBySupervisor(Integer id) {
		Optional<Usuario> optUser = usuarioRepository.findById(id);
		if(!optUser.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario no existe");
		return usuarioRepository.findByUsuarioResponsable(optUser.get());
	}

	@Override
	public Usuario validateUsuario(Operation operation, Usuario usuario) {

		if (operation == Operation.UPDATE || operation == Operation.DELETE) {
			Optional<Usuario> optUser;
			optUser = usuarioRepository.findById(usuario.getId());
			if (!optUser.isPresent())
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario no existe");

			if (!usuario.getCorreoElectronico().equals(optUser.get().getCorreoElectronico())) {
				Optional<Usuario> optUserEmail = usuarioRepository
						.findByCorreoElectronico(usuario.getCorreoElectronico());
				if (optUserEmail.isPresent())
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El correo electrónico ya está en uso");

			}
		}

		if (operation == Operation.CREATE) {
			Optional<Usuario> optUserEmail = usuarioRepository.findByCorreoElectronico(usuario.getCorreoElectronico());
			if (optUserEmail.isPresent())
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El correo electrónico ya está en uso");
		}

		Optional<Rol> optRol = rolRepository.findById(usuario.getRol().getId());
		if (!optRol.isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El rol no existe");

		if (usuario.getRol().getId() == RolEnum.SUPERVISED_STUDENT.getRol())
			usuario.setUsuarioResponsable(validateUsuario(operation, usuario.getUsuarioResponsable()));

		usuario.setRol(optRol.get());
		usuario.setFechaActualizacion(new Date());

		return usuario;
	}

}
