package com.up2promisedland.api.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.up2promisedland.api.entities.Grupo;
import com.up2promisedland.api.repositories.GrupoRepository;

@Component
public class GrupoBusinessImpl implements GrupoBusiness {

	@Autowired
	private GrupoRepository grupoRepository;

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

}
