package br.com.barata.microservice.fornecedor.controller;

import br.com.barata.microservice.fornecedor.dto.ItemDoPedidoDTO;
import br.com.barata.microservice.fornecedor.model.Pedido;
import br.com.barata.microservice.fornecedor.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;
	
	@RequestMapping(method = RequestMethod.POST)
	public Pedido realizaPedido(@RequestBody List<ItemDoPedidoDTO> produtos) {
		return pedidoService.realizaPedido(produtos);
	}
	
	@RequestMapping("/{id}")
	public Pedido getPedidoPorId(@PathVariable String id) {
		return pedidoService.getPedidoPorId(id);
	}
}
