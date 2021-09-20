package com.up2promisedland.api.conekta;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineItem {

	private String name;
	private String description;
	private long unit_price;
	private long quantity;
	private List<String> tags;
	private String type;

	public LineItem(String name, String description, long unitPrice, long quantity) {
		super();
		this.name = name;
		this.description = description;
		this.unit_price = unitPrice;
		this.quantity = quantity;
	}

}
