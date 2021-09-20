package com.up2promisedland.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.up2promisedland.api.beans.Filtros;
import com.up2promisedland.api.beans.PaymentProductWrapper;
import com.up2promisedland.api.beans.PaymentWrapper;
import com.up2promisedland.api.business.EmailBusiness;
import com.up2promisedland.api.conekta.ConektaPaymentTypeEnum;
import com.up2promisedland.api.conekta.ConektaStatusEnum;
import com.up2promisedland.api.entities.CarritoProductos;
import com.up2promisedland.api.entities.Direccion;
import com.up2promisedland.api.entities.OrdenPago;
import com.up2promisedland.api.entities.Pago;
import com.up2promisedland.api.entities.Producto;
import com.up2promisedland.api.entities.Usuario;
import com.up2promisedland.api.repositories.DireccionRepository;
import com.up2promisedland.api.repositories.OrdenPagoRepository;
import com.up2promisedland.api.repositories.PagoRepository;
import com.up2promisedland.api.repositories.ProductoRepository;
import com.up2promisedland.api.repositories.UsuarioRepository;
import com.up2promisedland.api.util.EmailContent;
import com.up2promisedland.api.util.EmailContentDetail;

import io.conekta.Charge;

@Service
public class OrdenPagoServiceImpl implements OrdenPagoService {

	@Autowired
	private OrdenPagoRepository ordenPagoRepository;

	@Autowired
	private PagoRepository pagoRepository;

	@Autowired
	private ConektaService conektaService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private DireccionRepository direccionRepository;

	@Autowired
	private ProductoRepository productoRepository;

	@Autowired
	private EmailBusiness emailBusiness;

	Gson gson = new GsonBuilder().setPrettyPrinting().create();

	@Override
	public OrdenPago getById(Integer id) {

		return ordenPagoRepository.findById(id).get();
	}

	@Override
	public Pago pay(PaymentWrapper pago) {

		Optional<Usuario> optUser = usuarioRepository
				.findByCorreoElectronico(pago.getDireccion().getUsuario().getCorreoElectronico());
		if (optUser.isPresent())
			pago.getDireccion().setUsuario(optUser.get());

		Direccion address = direccionRepository.save(pago.getDireccion());

		OrdenPago paymentOrder = new OrdenPago();

		List<CarritoProductos> products = new ArrayList<CarritoProductos>();
		for (PaymentProductWrapper product : pago.getProductos()) {
			products.add(new CarritoProductos(paymentOrder, productoRepository.findById(product.getProducto()).get(),
					product.getCantidad()));
		}

		paymentOrder.setUsuario(address.getUsuario());
		paymentOrder.setDireccion(address);
		paymentOrder.setCarrito(products);

		OrdenPago order = ordenPagoRepository.save(paymentOrder);

		Pago payment = this.sendToConekta(ConektaPaymentTypeEnum.CARD, order, pago);
		order.setEstatus(payment.getEstatus());
		order = ordenPagoRepository.save(order);

		emailBusiness.sendMail("ORDER_CONFIRMATION", this.generateEmailContent(payment));

		return payment;
	}

	@Override
	public List<OrdenPago> getPagos(Filtros filtros) {

		if (filtros.getTipo().equals("todo")) {
			return ordenPagoRepository.findByFechaLimiteBetweenOrderByFechaLimite(filtros.getFechaInicio(),
					filtros.getFechaFin());
		}

		if (filtros.getTipo().equals("usuario")) {
			List<Usuario> users = new ArrayList<Usuario>();
			for (String user : filtros.getValores()) {
				users.add(new Usuario(Integer.valueOf(user)));
			}
			if (filtros.getEstatus().length > 0)
				return ordenPagoRepository.findByUsuarioInAndEstatusInAndFechaLimiteBetweenOrderByFechaLimite(users,
						filtros.getEstatus(), filtros.getFechaInicio(), filtros.getFechaFin());
			return ordenPagoRepository.findByUsuarioInAndFechaLimiteBetweenOrderByFechaLimite(users,
					filtros.getFechaInicio(), filtros.getFechaFin());
		}

		if (filtros.getTipo().equals("producto")) {
			List<Producto> productos = new ArrayList<Producto>();
			for (String producto : filtros.getValores()) {
				productos.add(new Producto(Integer.valueOf(producto)));
			}
			if (filtros.getEstatus().length > 0)
				return ordenPagoRepository.findByCarritoAndEstatusInAndFechaLimiteBetweenOrderByFechaLimite(
						new CarritoProductos(), filtros.getEstatus(), filtros.getFechaInicio(), filtros.getFechaFin());
			return ordenPagoRepository.findByCarritoAndFechaLimiteBetweenOrderByFechaLimite(new CarritoProductos(null),
					filtros.getFechaInicio(), filtros.getFechaFin());

		}

		if (filtros.getTipo().equals("usuarioResponsable")) {
			List<Usuario> users = new ArrayList<Usuario>();
			for (String user : filtros.getValores()) {
				users.add(new Usuario(Integer.valueOf(user)));
			}
			if (filtros.getEstatus().length > 0)
				return ordenPagoRepository.findByUsuarioResponsableAndEstatusAndFechaLimiteBetween(users,
						filtros.getEstatus(), filtros.getFechaInicio(), filtros.getFechaFin());
			return ordenPagoRepository.findByUsuarioResponsableAndFechaLimiteBetween(users, filtros.getFechaInicio(),
					filtros.getFechaFin());

		}

		throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
				String.format("No existe el tipo de busqueda por %S", filtros.getTipo()));
	}

	@Override
	public Pago sendToConekta(ConektaPaymentTypeEnum tipo, OrdenPago ordenPago, PaymentWrapper payment) {

		if (tipo == ConektaPaymentTypeEnum.CARD) {

			Charge charge = conektaService.createCardPayment(ordenPago, payment.getConektaToken(), payment.isMsi());

			Pago newPayment = new Pago(ordenPago, charge.order_id, charge.id,
					ConektaStatusEnum.valueOf(charge.status.toUpperCase()).getStatus());

			if (payment.getFacturacion().isFacturar()) {
				newPayment.setRazonSocial(payment.getFacturacion().getRazonSocial());
				newPayment.setRfc(payment.getFacturacion().getRfc());
			}

			return pagoRepository.save(newPayment);
		}

		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo no soportado");
	}

	@Override
	public EmailContent generateEmailContent(Pago pago) {

		List<EmailContentDetail> details = new ArrayList<EmailContentDetail>();

		details.add(new EmailContentDetail("name", pago.getOrdenPago().getUsuario().getNombre()));
		details.add(new EmailContentDetail("order", pago.getConektaOrderId()));
		details.add(new EmailContentDetail("status", pago.getEstatus()));
		details.add(new EmailContentDetail("address", pago.getOrdenPago().getDireccion().toMailString()));

		return new EmailContent(pago.getOrdenPago().getUsuario().getCorreoElectronico(), "Recibo de pago", details);
	}

}
