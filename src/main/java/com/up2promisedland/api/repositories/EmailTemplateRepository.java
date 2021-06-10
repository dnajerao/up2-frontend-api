package com.up2promisedland.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.up2promisedland.api.entities.EmailTemplate;

public interface EmailTemplateRepository extends JpaRepository<EmailTemplate, Integer> {
	
	@Query("select template from EmailTemplate template where template.codigo_template = ?1")
	Optional<EmailTemplate> findByCodigoTemplate(String template);

}
