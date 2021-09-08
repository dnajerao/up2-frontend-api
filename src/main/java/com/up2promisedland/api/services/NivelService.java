package com.up2promisedland.api.services;

import java.util.List;

import com.up2promisedland.api.entities.Nivel;

public interface NivelService {

	public List<Nivel> getAll();

	public Nivel getNivelById(Integer id);

	public Nivel addNivel(Nivel Nivel);
}
