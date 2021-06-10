package com.up2promisedland.api.services;

import com.up2promisedland.api.util.EmailContent;

public interface EmailService {
	
	public boolean sendMail(String template, EmailContent content);

}
