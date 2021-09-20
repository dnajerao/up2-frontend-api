package com.up2promisedland.api.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.up2promisedland.api.business.EmailBusiness;
import com.up2promisedland.api.entities.Contacto;
import com.up2promisedland.api.repositories.ContactoRepository;
import com.up2promisedland.api.util.EmailContent;
import com.up2promisedland.api.util.EmailContentDetail;

@Service
public class ContactoServiceImpl implements ContactoService {

	@Autowired
	private ContactoRepository contactoRepository;

	@Autowired
	private EmailBusiness emailBusiness;

	@Override
	public Contacto addContacto(Contacto contacto) {
		List<EmailContentDetail> details = new ArrayList<EmailContentDetail>();
		details.add(new EmailContentDetail("name", contacto.getNombre() + " " + contacto.getApellido_paterno()));
		details.add(new EmailContentDetail("fullname",
				contacto.getNombre() + " " + contacto.getApellido_paterno() + " " + contacto.getApellido_materno()));
		details.add(new EmailContentDetail("email", contacto.getCorreo_electronico()));
		details.add(new EmailContentDetail("phone", contacto.getTelefono()));
		details.add(new EmailContentDetail("comment", contacto.getComentario()));

		EmailContent emailContent = new EmailContent(contacto.getCorreo_electronico(),
				"Gracias por contactarnos desde Up2PromisedLand.com!", details);

		emailBusiness.sendMail("CONTACTO", emailContent);

		return contactoRepository.save(contacto);
	}

}
