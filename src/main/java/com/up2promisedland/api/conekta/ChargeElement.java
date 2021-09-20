package com.up2promisedland.api.conekta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChargeElement {
	private PaymentMethod payment_method;
	private long amount;
}
