package com.up2promisedland.api.business;

import java.util.List;

import com.up2promisedland.api.entities.Nivel;

public interface NivelBusiness {

	public List<Nivel> getAll();

	public Nivel getNivelById(Integer id);

	public Nivel addNivel(Nivel Nivel);

}
