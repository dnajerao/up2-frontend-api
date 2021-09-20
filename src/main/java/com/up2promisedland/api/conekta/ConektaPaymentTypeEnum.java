package com.up2promisedland.api.conekta;

public enum ConektaPaymentTypeEnum {

	CARD("card");

	public final String type;

	private ConektaPaymentTypeEnum(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

}
