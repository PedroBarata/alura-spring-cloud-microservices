package br.com.barata.microservice.loja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import br.com.barata.microservice.loja.client.FornecedorClient;
import br.com.barata.microservice.loja.dto.CompraDTO;
import br.com.barata.microservice.loja.dto.InfoFornecedorDTO;
import br.com.barata.microservice.loja.dto.InfoPedidoDTO;
import br.com.barata.microservice.loja.model.Compra;
import br.com.barata.microservice.loja.repository.CompraRepository;

@Service
public class CompraService {
	String FORNECEDOR_BASE_URL = "http://fornecedor";

	@Autowired
	private FornecedorClient fornecedorClient;
	
	@Autowired
	private CompraRepository compraRepository;

	@HystrixCommand(fallbackMethod = "realizaCompraFallback", threadPoolKey = "realizaCompraThreadPool")
	public Compra realizaCompra(CompraDTO compraDTO) {
		InfoFornecedorDTO infoFornecedorDTO = fornecedorClient.getInfoPorEstado(compraDTO.getEndereco().getEstado());
		InfoPedidoDTO infoPedidoDTO = fornecedorClient.realizaPedido(compraDTO.getItens());
		Compra compraSalva = new Compra();
		compraSalva.setPedidoId(infoPedidoDTO.getId());
		compraSalva.setTempoDePreparo(infoPedidoDTO.getTempoDePreparo());
		compraSalva.setEnderecoDestino(compraDTO.getEndereco().toString());
		compraRepository.save(compraSalva);
		return compraSalva;
	}

	public Compra realizaCompraFallback(CompraDTO compraDTO) {
		Compra compraFallback = new Compra();
		compraFallback.setEnderecoDestino(compraDTO.getEndereco().toString());
		return compraFallback;
	}

	@HystrixCommand(threadPoolKey = "getByIdThreadPool")
	public Compra getById(String id) {
		return compraRepository.findById(id).orElse(new Compra());
	}
}
