package com.up2promisedland.api.conekta;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConektaCharge {
	private String currency;
	private Metadata metadata;
	private List<LineItem> line_items;
	private CustomerInfo customer_info;
	private List<ChargeElement> charges;

	public ConektaCharge(String currency, List<LineItem> lineItems, CustomerInfo customerInfo, List<ChargeElement> charges) {
		super();
		this.currency = currency;
		this.line_items = lineItems;
		this.customer_info = customerInfo;
		this.charges = charges;
	}

}
