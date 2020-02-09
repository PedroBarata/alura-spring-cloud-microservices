package br.com.barata.microservice.fornecedor.dto;

public class ItemDoPedidoDTO {

	private String id;
	
	private Integer quantidade;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}
