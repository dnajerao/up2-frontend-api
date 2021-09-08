package com.up2promisedland.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.up2promisedland.api.business.RolBusiness;
import com.up2promisedland.api.entities.Rol;

@Service
public class RolServiceImpl implements RolService{
	
	@Autowired
	private RolBusiness rolBusiness;	

	@Override
	public List<Rol> getAll() {
		return rolBusiness.getAll();
	}

	@Override
	public Rol getRolById(Integer id) {
		return rolBusiness.getRolById(id);
	}
	

}
