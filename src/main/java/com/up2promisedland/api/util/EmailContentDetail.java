package com.up2promisedland.api.util;

public class EmailContentDetail {

	private String key;
	private String value;

	public EmailContentDetail() {
		super();
	}

	public EmailContentDetail(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}