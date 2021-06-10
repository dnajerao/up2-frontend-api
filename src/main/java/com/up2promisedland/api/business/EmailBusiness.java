package com.up2promisedland.api.business;

import javax.mail.MessagingException;

import com.up2promisedland.api.util.EmailContent;

public interface EmailBusiness {

	public boolean sendMail(String template, EmailContent content) throws MessagingException;

}
