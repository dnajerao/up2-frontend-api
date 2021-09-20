package com.up2promisedland.api.conekta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethod {
	
	private String type;
	private String token_id;
	private Integer monthly_installments;

	public PaymentMethod(String type, String token_id) {
		super();
		this.type = type;
		this.token_id = token_id;
	}

}
