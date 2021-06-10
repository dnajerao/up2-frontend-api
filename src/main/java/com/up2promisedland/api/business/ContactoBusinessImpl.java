package com.up2promisedland.api.business;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.up2promisedland.api.entities.Contacto;
import com.up2promisedland.api.repositories.ContactoRepository;
import com.up2promisedland.api.util.EmailContent;
import com.up2promisedland.api.util.EmailContentDetail;

@Component
public class ContactoBusinessImpl implements ContactoBusiness{
	
	@Autowired
	private ContactoRepository contactoRepository;
	
	@Autowired
	private EmailBusiness emailBusiness;

	@Override
	public Contacto addContacto(Contacto contacto) {
		
		List<EmailContentDetail> details = new ArrayList<EmailContentDetail>();
		details.add(new EmailContentDetail("name", contacto.getNombre() + " " + contacto.getApellido_paterno()));
		details.add(new EmailContentDetail("fullname", contacto.getNombre() + " " + contacto.getApellido_paterno() + " " + contacto.getApellido_materno()));
		details.add(new EmailContentDetail("email", contacto.getCorreo_electronico()));
		details.add(new EmailContentDetail("phone", contacto.getTelefono()));
		details.add(new EmailContentDetail("comment", contacto.getComentario()));
		
		EmailContent emailContent = new EmailContent(contacto.getCorreo_electronico(), "Gracias por contactarnos desde Up2PromisedLand.com!", details);
		
		try {
			emailBusiness.sendMail("CONTACTO", emailContent);
		} catch (MessagingException e) {
			throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Mail service unavailable");
		}
		
		return contactoRepository.save(contacto);
	}
	
	

}
