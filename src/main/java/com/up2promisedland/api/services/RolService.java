package com.up2promisedland.api.services;

import java.util.List;

import com.up2promisedland.api.entities.Rol;

public interface RolService {
	
	public List<Rol> getAll();
	
	public Rol getRolById(Integer id);
}
