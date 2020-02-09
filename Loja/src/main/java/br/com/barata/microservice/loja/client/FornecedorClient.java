package br.com.barata.microservice.loja.client;

import java.util.List;

import br.com.barata.microservice.loja.dto.InfoPedidoDTO;
import br.com.barata.microservice.loja.dto.ItemCompraDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.barata.microservice.loja.dto.InfoFornecedorDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("fornecedor")
public interface FornecedorClient {

	@RequestMapping(value = "/info/{estado}")
	InfoFornecedorDTO getInfoPorEstado(@PathVariable("estado") String estado);

	@RequestMapping(value = "/info/")
	List<InfoFornecedorDTO> findAll();

	@RequestMapping(method = RequestMethod.POST, value = "/pedido")
	InfoPedidoDTO realizaPedido(List<ItemCompraDTO> itens);

}
