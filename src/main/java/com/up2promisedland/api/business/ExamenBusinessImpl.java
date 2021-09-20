package com.up2promisedland.api.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.up2promisedland.api.entities.Examen;
import com.up2promisedland.api.repositories.ExamenRepository;
import com.up2promisedland.api.util.EmailContent;
import com.up2promisedland.api.util.EmailContentDetail;

@Component
public class ExamenBusinessImpl implements ExamenBusiness {

	@Autowired
	private ExamenRepository examenRepository;

	@Autowired
	private EmailBusiness emailBusiness;

	@Override
	public Examen addExamen(Examen examen) {

		List<EmailContentDetail> details = new ArrayList<EmailContentDetail>();
		details.add(new EmailContentDetail("name", examen.getNombre() + " " + examen.getApellido_paterno()));
		details.add(new EmailContentDetail("fullname",
				examen.getNombre() + " " + examen.getApellido_paterno() + " " + examen.getApellido_materno()));
		details.add(new EmailContentDetail("email", examen.getCorreo_electronico()));
		details.add(new EmailContentDetail("phone", examen.getTelefono()));
		details.add(new EmailContentDetail("level", calculateLevel(examen.getCorrectas())));
		details.add(new EmailContentDetail("corrects", String.valueOf(examen.getCorrectas())));
		details.add(new EmailContentDetail("giftCode", String.valueOf(examen.getCodigo_regalo())));

		EmailContent emailContent = new EmailContent(examen.getCorreo_electronico(),
				"Te tenemos noticias! Aquí estan tus resultados!", details);

		emailBusiness.sendMail("EXAMEN", emailContent);

		return examenRepository.save(examen);
	}

	@Override
	public String calculateLevel(double corrects) {

		if (corrects > 80) {
			return "Advanced";
		}

		if (corrects > 60) {
			return "Upper Intermediate";
		}

		if (corrects > 50) {
			return "Intermediate";
		}

		if (corrects > 20) {
			return "Lower Intermediate";
		}

		if (corrects >= 0) {
			return "Elementary";
		}

		return "";
	}

}
