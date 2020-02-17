package br.com.barata.microservice.loja.dto;

import java.time.LocalDate;

public class VoucherDTO {

	private String entregaId;
	
	private LocalDate previsaoParaEntrega;

	public String getEntregaId() {
		return entregaId;
	}

	public void setEntregaId(String entregaId) {
		this.entregaId = entregaId;
	}

	public LocalDate getPrevisaoParaEntrega() {
		return previsaoParaEntrega;
	}

	public void setPrevisaoParaEntrega(LocalDate previsaoParaEntrega) {
		this.previsaoParaEntrega = previsaoParaEntrega;
	}
}
