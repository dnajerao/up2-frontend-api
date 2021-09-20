package com.up2promisedland.api.business;

import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.up2promisedland.api.entities.EmailTemplate;
import com.up2promisedland.api.repositories.EmailTemplateRepository;
import com.up2promisedland.api.util.EmailContent;
import com.up2promisedland.api.util.EmailContentDetail;

@Component
public class EmailBusinessImpl implements EmailBusiness {

	@Value("${spring.mail.username}")
	private String senderMail;

	@Autowired
	private EmailTemplateRepository emailTemplateRepository;

	@Autowired
	private JavaMailSender emailSender;

	@Override
	public boolean sendMail(String template, EmailContent content) {
		try {
			Optional<EmailTemplate> emailTemplate = emailTemplateRepository.findByCodigoTemplate(template);

			if (!emailTemplate.isPresent())
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "template not exists");

			String body = emailTemplate.get().getHtml();

			for (EmailContentDetail detail : content.getDetails()) {
				String token = "[" + detail.getKey() + "]";
				body = body.replace(token, detail.getValue());
			}

			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);
			helper.setFrom(senderMail);
			helper.setTo(content.getTo());
			helper.setCc(senderMail);
			helper.setSubject(content.getSubject());

			helper.setText(body, true);

			emailSender.send(message);
			return true;

		} catch (MessagingException e) {
			throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, e.getMessage());
		}
	}

}
