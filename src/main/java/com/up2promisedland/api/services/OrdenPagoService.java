package com.up2promisedland.api.services;

import java.util.List;

import com.up2promisedland.api.beans.Filtros;
import com.up2promisedland.api.beans.PaymentWrapper;
import com.up2promisedland.api.conekta.ConektaPaymentTypeEnum;
import com.up2promisedland.api.entities.OrdenPago;
import com.up2promisedland.api.entities.Pago;
import com.up2promisedland.api.util.EmailContent;

public interface OrdenPagoService {

	public OrdenPago getById(Integer id);

	public Pago pay(PaymentWrapper pago);

	public List<OrdenPago> getPagos(Filtros filtros);

	public Pago sendToConekta(ConektaPaymentTypeEnum tipo, OrdenPago ordenPago, PaymentWrapper pago);
	
	public EmailContent generateEmailContent(Pago pago);

}
