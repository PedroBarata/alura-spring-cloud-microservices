package br.com.barata.microservice.loja.service;

import br.com.barata.microservice.loja.client.FornecedorClient;
import br.com.barata.microservice.loja.client.TransportadorClient;
import br.com.barata.microservice.loja.dto.*;
import br.com.barata.microservice.loja.enums.CompraStatus;
import br.com.barata.microservice.loja.model.Compra;
import br.com.barata.microservice.loja.repository.CompraRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CompraService {
	String FORNECEDOR_BASE_URL = "http://fornecedor";

	@Autowired
	private FornecedorClient fornecedorClient;

	@Autowired
	private TransportadorClient transportadorClient;
	
	@Autowired
	private CompraRepository compraRepository;

	//Podemos usar esses métodos para reprocessar, cancelar a compra, a partir do estado da compra.
/*	public Compra reprocessaCompra(String id) {
		return null;
	}*/

	@HystrixCommand(fallbackMethod = "realizaCompraFallback", threadPoolKey = "realizaCompraThreadPool")
	public Compra realizaCompra(CompraDTO compraDTO) {

		Compra compraSalva = new Compra();
		compraSalva.setEnderecoDestino(compraDTO.getEndereco().toString());
		compraSalva.setCompraStatus(CompraStatus.RECEBIDO);
		compraRepository.save(compraSalva);
		compraDTO.setCompraId(compraSalva.getId());

		InfoFornecedorDTO infoFornecedorDTO = fornecedorClient.getInfoPorEstado(compraDTO.getEndereco().getEstado());
		InfoPedidoDTO infoPedidoDTO = fornecedorClient.realizaPedido(compraDTO.getItens());
		compraSalva.setPedidoId(infoPedidoDTO.getId());
		compraSalva.setTempoDePreparo(infoPedidoDTO.getTempoDePreparo());

		compraSalva.setCompraStatus(CompraStatus.PEDIDO_REALIZADO);
		compraRepository.save(compraSalva);

		//if(true) throw new RuntimeException(); Caso dê erro aqui, o fallback irá retornar o processo de compra no estado atual
		InfoEntregaDTO entregaDTO = new InfoEntregaDTO();
		entregaDTO.setPedidoId(infoPedidoDTO.getId());
		entregaDTO.setDataParaEntrega(LocalDate.now().plusDays(infoPedidoDTO.getTempoDePreparo()));
		entregaDTO.setEnderecoOrigem(infoFornecedorDTO.getEndereco());
		entregaDTO.setEnderecoDestino(compraDTO.getEndereco().toString());
		VoucherDTO voucherDTO = transportadorClient.reservaEntrega(entregaDTO);
		compraSalva.setDataParaEntrega(voucherDTO.getPrevisaoParaEntrega());
		compraSalva.setEntregaId(voucherDTO.getEntregaId());
		compraSalva.setCompraStatus(CompraStatus.RESERVA_ENTREGA_REALIZADA);
		compraRepository.save(compraSalva);

		return compraSalva;
	}

	public Compra realizaCompraFallback(CompraDTO compraDTO) {
		if(compraDTO.getCompraId() != null) {
			return compraRepository.findById(compraDTO.getCompraId()).get();
		}

		Compra compraFallback = new Compra();
		compraFallback.setEnderecoDestino(compraDTO.getEndereco().toString());
		return compraFallback;
	}

	@HystrixCommand(threadPoolKey = "getByIdThreadPool")
	public Compra getById(String id) {
		return compraRepository.findById(id).orElse(new Compra());
	}
}
