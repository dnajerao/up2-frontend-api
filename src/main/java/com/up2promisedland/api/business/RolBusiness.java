package com.up2promisedland.api.business;

import java.util.List;

import com.up2promisedland.api.entities.Rol;

public interface RolBusiness {
	
	public List<Rol> getAll();
	
	public Rol getRolById(Integer id);

}
