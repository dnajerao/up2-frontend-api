package com.up2promisedland.api.conekta;

public enum ConektaStatusEnum {
	
	PENDING_PAYMENT("Pendiente de pago"),
	PAID("Pagado"),
	PREAUTH("Pre autorizado"),
	EXPIRED("Expirado"),
	VOIDED("Anulada"),
	FRAUDULENT("Fraudulento"),
	PREAUTHORIZED("Pre autorizado"),
	CANCELED("Cancelado"),
	PENDING_CONFIRMATION("Pendiente confirmación"),
	CHARGED_BACK("Devuelto"),
	PARTIALLY_REFUNDED("Parcialmente devuelto"),
	REFUNDED("Reintegrado"),
	REVERSED("Reversado"),
	APPROVED("Aprobado"),
	DECLINED("Rechazado"),
	IN_REVIEW("En revisión"),
	INSUFFICIENT_FUNDS("Fondos insuficientes"),
	CARD_DECLINED("Tarjeta rechazada"),
	STOLEN_CARD("Tarjeta robada"),
	SUSPECTED_FRAUD("Sospecha fraude"),
	UNPROCESSABLE_CARD_TYPE("Tipo tarjeta no soportado");
	
	public final String status;
	
	private ConektaStatusEnum(String status) {
		this.status = status;
	}
	
	

	public String getStatus() {
		return status;
	}
	
}
