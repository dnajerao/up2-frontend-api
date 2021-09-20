package com.up2promisedland.api.services;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.up2promisedland.api.conekta.ChargeElement;
import com.up2promisedland.api.conekta.ConektaCharge;
import com.up2promisedland.api.conekta.CustomerInfo;
import com.up2promisedland.api.conekta.LineItem;
import com.up2promisedland.api.conekta.PaymentMethod;
import com.up2promisedland.api.entities.CarritoProductos;
import com.up2promisedland.api.entities.OrdenPago;

import io.conekta.Charge;
import io.conekta.Conekta;
import io.conekta.Error;
import io.conekta.ErrorList;
import io.conekta.Order;

@Service
public class ConektaServiceImpl implements ConektaService {

	Logger log = LoggerFactory.getLogger(ConektaServiceImpl.class);

	@Value("${conekta.api.secret}")
	private String conektaApiSecret;

	Gson gson = new GsonBuilder().setPrettyPrinting().create();

	@Override
	public Charge createCardPayment(OrdenPago orden, String token, Boolean msi) {
		Conekta.setApiKey(conektaApiSecret);

		try {

			Order completeOrder = Order.create(new JSONObject(gson.toJson(parseCharge(orden, msi, token))));
			log.info("Conekta Response {}", completeOrder);

			return (Charge) completeOrder.charges.get(0);

		} catch (JSONException e) {
			log.error(e.getStackTrace().toString());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al generar comunicación con Conekta");
		} catch (Error e) {
			log.error(e.getStackTrace().toString());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error desconocido");
		} catch (ErrorList e) {
			log.error("Conekta", e.details.get(0));
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.details.get(0).message_to_purchaser);
		}
	}

	@Override
	public ConektaCharge parseCharge(OrdenPago payment, Boolean msi, String token) {

		CustomerInfo customerInfo = new CustomerInfo(
				String.format("%s %s %s", payment.getUsuario().getNombre(), payment.getUsuario().getApellidoPaterno(),
						payment.getUsuario().getApellidoMaterno()),
				payment.getUsuario().getTelefono(), payment.getUsuario().getCorreoElectronico());

		long amount = 0;
		List<LineItem> lineItems = new ArrayList<LineItem>();
		for (CarritoProductos item : payment.getCarrito()) {

			Double price;
			if (msi)
				price = item.getProducto().getPrecioMsi();
			else
				price = item.getProducto().getPrecio();

			lineItems.add(new LineItem(item.getProducto().getProducto(), item.getProducto().getDescripcion(),
					(long) (price * 100), item.getCantidad()));

			amount += price * item.getCantidad();
		}

		List<ChargeElement> charges = new ArrayList<ChargeElement>();
		ChargeElement element = new ChargeElement(new PaymentMethod("card", token), (amount * 100));

		if (msi)
			element.getPayment_method().setMonthly_installments(3);

		charges.add(element);

		return new ConektaCharge("mxn", lineItems, customerInfo, charges);
	}

}
