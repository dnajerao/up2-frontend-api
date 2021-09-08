package com.up2promisedland.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.up2promisedland.api.business.NivelBusiness;
import com.up2promisedland.api.entities.Nivel;

@Service
public class NivelServiceImpl implements NivelService {

	@Autowired
	private NivelBusiness nivelBusiness;

	@Override
	public List<Nivel> getAll() {
		return nivelBusiness.getAll();
	}

	@Override
	public Nivel getNivelById(Integer id) {
		return nivelBusiness.getNivelById(id);
	}

	@Override
	public Nivel addNivel(Nivel Nivel) {
		return nivelBusiness.addNivel(Nivel);
	}

}
