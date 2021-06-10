package com.up2promisedland.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.up2promisedland.api.business.ExamenBusiness;
import com.up2promisedland.api.entities.Examen;

@Service
public class ExamenServiceImpl implements ExamenService {

	@Autowired
	private ExamenBusiness examenBusiness;

	@Override
	public Examen addExamen(Examen examen) {
		return examenBusiness.addExamen(examen);
	}

}
