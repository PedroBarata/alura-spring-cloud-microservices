package br.com.barata.microservice.loja.service;

import br.com.barata.microservice.loja.dto.InfoPedidoDTO;
import br.com.barata.microservice.loja.model.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.barata.microservice.loja.client.FornecedorClient;
import br.com.barata.microservice.loja.dto.CompraDTO;
import br.com.barata.microservice.loja.dto.InfoFornecedorDTO;

@Service
public class CompraService {
	String FORNECEDOR_BASE_URL = "http://fornecedor";

	@Autowired
	private FornecedorClient fornecedorClient;

	public Compra realizaCompra(CompraDTO compraDTO) {
		InfoFornecedorDTO infoFornecedorDTO = fornecedorClient.getInfoPorEstado(compraDTO.getEndereco().getEstado());
		InfoPedidoDTO infoPedidoDTO = fornecedorClient.realizaPedido(compraDTO.getItens());
		Compra compraSalva = new Compra();
		compraSalva.setPedidoId(infoPedidoDTO.getId());
		compraSalva.setTempoDePreparo(infoPedidoDTO.getTempoDePreparo());
		compraSalva.setEnderecoDestino(compraDTO.getEndereco().toString());
		return compraSalva;
	}
}
