package com.up2promisedland.api.util;

import java.util.List;

public class EmailContent {

	private String to;
	private String subject;
	private List<EmailContentDetail> details;

	public EmailContent() {
		super();
	}

	public EmailContent(String to, String subject, List<EmailContentDetail> details) {
		super();
		this.to = to;
		this.subject = subject;
		this.details = details;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public List<EmailContentDetail> getDetails() {
		return details;
	}

	public void setDetails(List<EmailContentDetail> details) {
		this.details = details;
	}

}
