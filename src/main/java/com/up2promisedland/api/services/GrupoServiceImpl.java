package com.up2promisedland.api.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.up2promisedland.api.beans.UsuariosGrupoWrapper;
import com.up2promisedland.api.entities.Grupo;
import com.up2promisedland.api.entities.Usuario;
import com.up2promisedland.api.entities.UsuariosGrupo;
import com.up2promisedland.api.repositories.GrupoRepository;
import com.up2promisedland.api.repositories.UsuarioRepository;
import com.up2promisedland.api.repositories.UsuariosGrupoRepository;

@Service
public class GrupoServiceImpl implements GrupoService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private GrupoRepository grupoRepository;

	@Autowired
	private UsuariosGrupoRepository usuariosGrupoRepository;

	@Override
	public List<Grupo> getAll() {
		return grupoRepository.findAll();
	}

	@Override
	public Grupo getGrupoById(Integer id) {
		return grupoRepository.findById(id).get();
	}

	@Override
	public Grupo addGrupo(Grupo grupo) {
		return grupoRepository.save(grupo);
	}
	
	@Override
	public List<Usuario> getUsuariosWOGrupo() {
		return usuarioRepository.findWithoutGroup();
	}

	@Override
	public List<UsuariosGrupo> getUsuariosByGrupoId(Integer id) {
		Grupo group = grupoRepository.findById(id).get();
		return usuariosGrupoRepository.findByGrupo(group);
	}

	@Override
	public List<UsuariosGrupo> addUsuariosToGroupId(Integer grupo, UsuariosGrupoWrapper usuarios) {

		List<UsuariosGrupo> usersGroup = new ArrayList<UsuariosGrupo>();

		for (Integer user : usuarios.getUsuariosGrupo()) {
			usersGroup.add(new UsuariosGrupo(new Grupo(grupo), new Usuario(user), "Activo", null, null));
		}

		for (UsuariosGrupo user : usersGroup) {
			usuariosGrupoRepository.save(user);
		}

		return usuariosGrupoRepository.findByGrupo(new Grupo(grupo));
	}

}
