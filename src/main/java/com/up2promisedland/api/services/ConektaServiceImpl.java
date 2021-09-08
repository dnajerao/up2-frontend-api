package com.up2promisedland.api.services;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.up2promisedland.api.conekta.ConektaCustomer;
import com.up2promisedland.api.entities.Usuario;
import com.up2promisedland.api.repositories.UsuarioRepository;

import io.conekta.Conekta;
import io.conekta.ConektaList;
import io.conekta.Customer;
import io.conekta.Error;
import io.conekta.ErrorList;
import io.conekta.Order;

@Service
public class ConektaServiceImpl implements ConektaService {

	Logger log = LoggerFactory.getLogger(ConektaServiceImpl.class);

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Value("${conekta.api.secret}")
	private String conektaApiSecret;

	@Override
	public Usuario createCustomer(Usuario usuario) {

		Conekta.setApiKey(conektaApiSecret);

		try {

			ConektaCustomer conektaCustomer = new ConektaCustomer(
					usuario.getNombre() + " " + usuario.getApellidoPaterno() + " " + usuario.getApellidoMaterno(),
					usuario.getCorreoElectronico(), usuario.getTelefono());

			JSONObject obj = new JSONObject(conektaCustomer);
			
			Customer customer = Customer.create(obj);

			usuario.setIdConekta(customer.getId());
			usuario.setFechaActualizacion(new Date());

			return usuarioRepository.save(usuario);

		} catch (JSONException e) {
			log.error(e.getStackTrace().toString());
		} catch (Error e) {
			log.error(e.getStackTrace().toString());
		} catch (ErrorList e) {
			log.error("Conekta", e.details.get(0));
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.details.get(0).message_to_purchaser);
		}

		return null;
	}

	@Override
	public ConektaList createCardPayment() {
		try {
			Conekta.setApiKey(conektaApiSecret);
			JSONObject completeOrderJSON = new JSONObject("{" + "'currency': 'mxn'," + "'metadata': {"
					+ "    'test': true" + "}," + "'line_items': [{" + "    'name': 'Box of Cohiba S1s',"
					+ "    'description': 'Imported From Mex.'," + "    'unit_price': 35000," + "    'quantity': 1,"
					+ "    'tags': ['food', 'mexican food']," + "    'type': 'physical'" + "}]," + "'customer_info': { "
					+ "    'name': 'John Constantine'," + "    'phone': '+5213353319758',"
					+ "    'email': 'dnajera0806@gmail.com'" + "}," + "'charges': [{" + "    'payment_method': {"
					+ "        'type': 'card'," + "        'token_id': 'tok_test_visa_4242'" + "    }, "
					+ "    'amount': 35000" + "}]" + "}");

			Order completeOrder = Order.create(completeOrderJSON);

			return completeOrder.charges;
		} catch (Error e) {
			e.printStackTrace();
		} catch (ErrorList e) {
			log.error(e.getMessage());
		}
		return null;
	}

}
