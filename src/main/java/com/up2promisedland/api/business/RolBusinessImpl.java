package com.up2promisedland.api.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.up2promisedland.api.entities.Rol;
import com.up2promisedland.api.repositories.RolRepository;

@Component
public class RolBusinessImpl implements RolBusiness{
	
	@Autowired
	private RolRepository rolRepository;

	@Override
	public List<Rol> getAll() {
		return rolRepository.findAll();
	}

	@Override
	public Rol getRolById(Integer id) {
		return rolRepository.findById(id).get();
	}
	
}
