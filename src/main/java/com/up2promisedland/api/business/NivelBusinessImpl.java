package com.up2promisedland.api.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.up2promisedland.api.entities.Nivel;
import com.up2promisedland.api.repositories.NivelRepository;

@Component
public class NivelBusinessImpl implements NivelBusiness {

	@Autowired
	private NivelRepository nivelRepository;

	@Override
	public List<Nivel> getAll() {
		return nivelRepository.findAll();
	}

	@Override
	public Nivel getNivelById(Integer id) {
		return nivelRepository.findById(id).get();
	}

	@Override
	public Nivel addNivel(Nivel Nivel) {
		return nivelRepository.save(Nivel);
	}

}
