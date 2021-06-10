package com.up2promisedland.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.up2promisedland.api.business.ContactoBusiness;
import com.up2promisedland.api.entities.Contacto;


@Service
public class ContactoServiceImpl implements ContactoService {
	
	@Autowired
	private ContactoBusiness contactoBusiness;

	@Override
	public Contacto addContacto(Contacto contacto) {
		return contactoBusiness.addContacto(contacto);
	}

}
