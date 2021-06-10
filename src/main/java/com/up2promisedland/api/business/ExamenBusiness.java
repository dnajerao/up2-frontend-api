package com.up2promisedland.api.business;

import com.up2promisedland.api.entities.Examen;

public interface ExamenBusiness {

	public Examen addExamen(Examen examen);
	
	public String calculateLevel(double corrects);

}
