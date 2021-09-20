package com.up2promisedland.api.conekta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerInfo {
	private String customer_id;
	private String name;
	private String phone;
	private String email;

	public CustomerInfo(String customer_id) {
		super();
		this.customer_id = customer_id;
	}

	public CustomerInfo(String name, String phone, String email) {
		super();
		this.name = name;
		this.phone = phone;
		this.email = email;
	}

}
